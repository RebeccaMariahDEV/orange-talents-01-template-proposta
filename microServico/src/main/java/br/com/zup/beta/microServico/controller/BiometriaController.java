package br.com.zup.beta.microServico.controller;

import br.com.zup.beta.microServico.core.BaseController;
import br.com.zup.beta.microServico.dto.BiometriaDto;
import br.com.zup.beta.microServico.model.biometria.Biometria;
import br.com.zup.beta.microServico.model.cartoes.CartoesGerados;
import br.com.zup.beta.microServico.repository.BiometriaRepositpry;
import br.com.zup.beta.microServico.repository.CartoesGeradosRepository;
import org.h2.api.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/biometrias")
public class BiometriaController extends BaseController {

    BiometriaRepositpry biometriaRepositpry;
    CartoesGeradosRepository cartoesGeradosRepository;

    @Autowired
    public BiometriaController(BiometriaRepositpry biometriaRepositpry,
                               CartoesGeradosRepository cartoesGeradosRepository){
        this.biometriaRepositpry = biometriaRepositpry;
        this.cartoesGeradosRepository = cartoesGeradosRepository;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> criarBiometria(@PathVariable(value = "id") Long idCartao, @Valid @RequestBody BiometriaDto biometriaDto) {
        Biometria biometria = new Biometria();
        var findId = cartoesGeradosRepository.findById(idCartao);
        System.out.println(findId.get());
        biometria.setBiometria(biometriaDto.getBiometria(), findId.get());
        System.out.println(biometria.getIdCartao());
        if (biometria.estaEmBase64() == true) {
            System.out.println(biometria.getBiometria());
            try {
                biometriaRepositpry.save(biometria);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (Exception exception) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
