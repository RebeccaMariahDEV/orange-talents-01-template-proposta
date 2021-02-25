package br.com.zup.beta.microServico.controller;

import br.com.zup.beta.microServico.core.config.IpUserAgent;
import br.com.zup.beta.microServico.dto.BloqueioDto;
import br.com.zup.beta.microServico.model.bloqueio.BloqueioCartao;
import br.com.zup.beta.microServico.model.interfaces.BloqueioCartoes;
import br.com.zup.beta.microServico.repository.BloqueioRepository;
import br.com.zup.beta.microServico.repository.CartoesGeradosRepository;
import br.com.zup.beta.microServico.repository.NovaPropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("api/cartoes")
public class BloqueioController {

    BloqueioRepository bloqueioRepository;
    CartoesGeradosRepository cartoesGeradosRepository;
    private final NovaPropostaRepository novaPropostaRepository;
    private final IpUserAgent ipUserAgent;
    private final BloqueioCartoes bloqueioCartoes;


    @Autowired
    public BloqueioController(
            NovaPropostaRepository novaPropostaRepository,
            IpUserAgent ipUserAgent,
            CartoesGeradosRepository cartoesGeradosRepository,
            BloqueioRepository bloqueioRepository, BloqueioCartoes bloqueioCartoes) {
        this.bloqueioRepository = bloqueioRepository;
        this.novaPropostaRepository = novaPropostaRepository;
        this.ipUserAgent = ipUserAgent;
        this.cartoesGeradosRepository = cartoesGeradosRepository;
        this.bloqueioCartoes = bloqueioCartoes;
    }

    @Transactional
    @PostMapping("bloqueios/{id}")
    public ResponseEntity<?> bloqueio(@RequestBody @Valid BloqueioDto bloqueioDto,
                                      HttpServletRequest request,
                                      @PathVariable(value = "id") Long id) {

        var findCartao = cartoesGeradosRepository.findByIdcartao(bloqueioDto.getIdcartao());
        if (findCartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não identificado");
        }

        var cartao = findCartao.get();
        if (!cartao.isAtivo()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Este cartão encontra-se bloqueado");
        }

        cartao.setAtivo(false);

        // registrar bloqueio do cartão
        var userAgente = new IpUserAgent().getRequestHeaders(request);
        BloqueioCartao bloqueioCartao = new BloqueioCartao(userAgente.get("user-agent"), userAgente.get("ip"), cartao);
        bloqueioRepository.save(bloqueioCartao);

        //feing para avisar o sistema legado
        Optional<BloqueioCartao> findIdcartao = bloqueioRepository.findById(id);
        if (findIdcartao.isPresent()){
            BloqueioCartoes.BloqueioRequest requeste = new BloqueioCartoes.BloqueioRequest(findIdcartao.get());
            try {
                BloqueioCartoes.BloqueioResponse resposta  =
                        bloqueioCartoes.bloqueiaCartao(
                                new BloqueioCartoes.BloqueioRequest(bloqueioCartao),
                                cartao.getcartaoId());

                System.out.println(bloqueioCartao.getUserAgent());
                System.out.println(resposta);
                System.out.println("foi aqui");
                cartoesGeradosRepository.save(cartao);
                return ResponseEntity.ok().body(resposta + "Cartão bloqueado com sucesso");
            }catch (FeignException exception){
                System.out.println("Deu ruim");
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
