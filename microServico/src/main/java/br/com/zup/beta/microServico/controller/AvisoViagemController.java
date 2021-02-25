package br.com.zup.beta.microServico.controller;

import br.com.zup.beta.microServico.core.config.IpUserAgent;
import br.com.zup.beta.microServico.dto.AvisoViagemDto;
import br.com.zup.beta.microServico.model.cartoes.CartoesGerados;
import br.com.zup.beta.microServico.model.interfaces.AvisosViagens;
import br.com.zup.beta.microServico.model.viagem.AvisoViagem;
import br.com.zup.beta.microServico.repository.AvisoViagemRepository;
import br.com.zup.beta.microServico.repository.CartoesGeradosRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("api/")
@Controller
public class AvisoViagemController {

    AvisoViagemRepository avisoViagemRepository;
    CartoesGeradosRepository cartoesGeradosRepository;
    private final IpUserAgent ipUserAgent;
    private final AvisosViagens avisosViagens;

    @Autowired
    public AvisoViagemController(AvisoViagemRepository avisoViagemRepository,
                                 CartoesGeradosRepository cartoesGeradosRepository,
                                 IpUserAgent ipUserAgent,
                                 AvisosViagens avisosViagens) {
        this.avisoViagemRepository = avisoViagemRepository;
        this.cartoesGeradosRepository = cartoesGeradosRepository;
        this.ipUserAgent = ipUserAgent;
        this.avisosViagens = avisosViagens;

    }

    @PostMapping("avisos/{id}")
    public ResponseEntity<?> avisoDeViagem(@RequestBody @Valid AvisoViagemDto avisoViagemDto,
                                           @PathVariable("id") Long id,
                                           HttpServletRequest request) {

        //pesquisas
        var userAgente = new IpUserAgent().getRequestHeaders(request);
        Optional<CartoesGerados> findIdCartao = cartoesGeradosRepository.findById(id);

        //validar id do cartão
        if (findIdCartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado ");
        }

        //criando os dados para tabela de avisos
        AvisoViagem avisoViagem = new AvisoViagem(avisoViagemDto.getDestinoViagem(), avisoViagemDto.getTerminoViagem(),
                userAgente.get("user-agent"), userAgente.get("ip"), findIdCartao.get());
        avisoViagemRepository.save(avisoViagem);

        try {
            //aviso da viagem
            AvisosViagens.AvisoRequest avisoRequest = new AvisosViagens.AvisoRequest(avisoViagem);

            AvisosViagens.AvisoResponse response =
                    avisosViagens.avisos(avisoRequest, findIdCartao.get().getcartaoId());

            System.out.println(response);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (FeignException exception) {
            System.out.println("deu ruim 2 "+exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }
}
