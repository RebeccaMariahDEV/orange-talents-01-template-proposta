package br.com.zup.beta.microServico.controller;

import br.com.zup.beta.microServico.core.BaseController;
import br.com.zup.beta.microServico.core.erros.tipos.ColunaErro;
import br.com.zup.beta.microServico.dto.NovaPropostaDto;
import br.com.zup.beta.microServico.model.NovaProposta;
import br.com.zup.beta.microServico.repository.NovaPropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.annotation.WebServlet;
import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/proposta")
@WebServlet
public class NovaPropostaController extends BaseController {

    NovaPropostaRepository novaPropostaRepository;

    @Autowired
    public NovaPropostaController(NovaPropostaRepository novaPropostaRepository) {
        this.novaPropostaRepository = novaPropostaRepository;
    }



    @PostMapping
    public ResponseEntity<?> criarProposta(@Valid @RequestBody NovaPropostaDto novaPropostaDto) {
        NovaProposta novaProposta = new NovaProposta();

        novaProposta.setProposta(novaPropostaDto.getNome(), novaPropostaDto.getSobrenome(), novaPropostaDto.getEmail(),
                novaPropostaDto.getEndereco(), novaPropostaDto.getSalario(), novaPropostaDto.getDocumentoId());

        var findDocumento = novaPropostaRepository.findByDocumentoId(novaProposta.getDocumento_id());

        if(findDocumento.isPresent()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        var newEntity = novaPropostaRepository.save(novaProposta);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/proposta")
                .buildAndExpand(newEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping
    public ResponseEntity<?> pesquisarRestricao(@Valid @RequestBody NovaProposta novaProposta){



        return null;

    }

}
