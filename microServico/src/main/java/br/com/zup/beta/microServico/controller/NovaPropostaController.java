package br.com.zup.beta.microServico.controller;

import br.com.zup.beta.microServico.core.BaseController;
import br.com.zup.beta.microServico.dto.NovaPropostaDto;
import br.com.zup.beta.microServico.model.*;
import br.com.zup.beta.microServico.model.cartoes.CartoesGerados;
import br.com.zup.beta.microServico.model.interfaces.AnaliseClient;
import br.com.zup.beta.microServico.model.interfaces.Cartoes;
import br.com.zup.beta.microServico.model.interfaces.PesquisaPropostas;
import br.com.zup.beta.microServico.model.proporsta.NovaProposta;
import br.com.zup.beta.microServico.repository.CartoesGeradosRepository;
import br.com.zup.beta.microServico.repository.NovaPropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@Controller
@RequestMapping("/api")
public class NovaPropostaController extends BaseController {

    NovaPropostaRepository novaPropostaRepository;
    private final AnaliseClient analiseClient;
    private final Cartoes cartoes;
    private final PesquisaPropostas pesquisaPropostas;
    CartoesGeradosRepository cartoesGeradosRepository;


    @Autowired
    public NovaPropostaController(NovaPropostaRepository novaPropostaRepository,
                                  AnaliseClient analiseClient, Cartoes cartoes,
                                  PesquisaPropostas pesquisaPropostas,
                                  CartoesGeradosRepository cartoesGeradosRepository) {
        this.novaPropostaRepository = novaPropostaRepository;
        this.analiseClient = analiseClient;
        this.cartoes = cartoes;
        this.pesquisaPropostas = pesquisaPropostas;
        this.cartoesGeradosRepository = cartoesGeradosRepository;

    }

    @Transactional
    @PostMapping("/propostas")
    public ResponseEntity<?> criarProposta(@Valid @RequestBody NovaPropostaDto novaPropostaDto, UriComponentsBuilder uriBuilder) {
        NovaProposta novaProposta = new NovaProposta();

        novaProposta.setProposta(novaPropostaDto.getNome(), novaPropostaDto.getSobrenome(), novaPropostaDto.getEmail(),
                novaPropostaDto.getEndereco(), novaPropostaDto.getSalario(), novaPropostaDto.getDocumentoId());

        var findDocumento = novaPropostaRepository.findByDocumentoId(novaProposta.getDocumentoId());

        if (findDocumento.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        novaPropostaRepository.save(novaProposta);

        AnaliseClient.ConsultaStatusRequest requisicaoAnalise = new AnaliseClient.ConsultaStatusRequest(novaProposta);
        try {
            AnaliseClient.ConsultaStatusResponse resposta = analiseClient.consulta(requisicaoAnalise);
            novaProposta.atualizaStatus(resposta.getResultadoSolicitacao());
        } catch (FeignException.UnprocessableEntity e) {
            novaProposta.atualizaStatus("COM_RESTRICAO");
            novaPropostaRepository.save(novaProposta);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        novaPropostaRepository.save(novaProposta);


        URI location = uriBuilder.path("/api/propostas/{id}")
                .buildAndExpand(novaProposta.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @GetMapping("/cartoes/{id}")
    public ResponseEntity<?> consultaCartao(@PathVariable(value="id") Long propostaId){

        Optional<NovaProposta> proposta = novaPropostaRepository.findById(propostaId);
        System.out.println(proposta);
        if (proposta.isPresent()){
            PesquisaPropostas.AnaliseRequest analise = new PesquisaPropostas.AnaliseRequest(proposta.get());
            System.out.println(proposta.get());
            try {
                System.out.println("Proposta aceita");

                PesquisaPropostas.AnaliseResponse resposta = pesquisaPropostas.analiseCartao(analise.getIdProposta());
                CartoesGerados cartoesGerados = new CartoesGerados(resposta.getId(), proposta.get(), resposta.getEmitidoEm());
                cartoesGeradosRepository.save(cartoesGerados);
                System.out.println(cartoesGerados);
                System.out.println("Proposta salva");
                return ResponseEntity.status(HttpStatus.OK).body(resposta);
            }catch (FeignException.NotFound e){
                System.out.println("Proposta recusada");

                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proposta não existente");
            }
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    //lembrar de mudar ao fim do programa
    @Transactional
    @Scheduled(cron = "0 0/10 * * * *")
    public void atualizacao() {
        var propostas = novaPropostaRepository.findByStatusAndProcessado(Status.LEGIVEL, false);

        if (propostas.isEmpty()) {
            System.out.println("Sem propostas para processar");
            return;
        }


        propostas.forEach(proposta -> {
            System.out.println("Analizando proposta com id " + proposta.getId());
            Cartoes.NovoCartaoRequest requisicaoCartao = new Cartoes.NovoCartaoRequest(proposta);
            System.out.println(proposta.getId());
            try {
                Cartoes.NovoCartaoResponse response = cartoes.novoCartao(requisicaoCartao);
                proposta.setProcessado();
                System.out.println(response);
                novaPropostaRepository.save(proposta);
                System.out.println("Cartão da proposta criado com sucesso");
                return;
            } catch (FeignException.UnprocessableEntity e) {
                System.out.println("Não foi possivel criar o cartão da proposta " + proposta.getId());
                return ;
            }
        });
    }

}
