package br.com.zup.beta.microServico.controller;

import br.com.zup.beta.microServico.dto.CarteirasPayDto;
import br.com.zup.beta.microServico.model.carteirasPay.CarteirasPay;
import br.com.zup.beta.microServico.model.cartoes.CartoesGerados;
import br.com.zup.beta.microServico.model.interfaces.CarteirasSmPay;
import br.com.zup.beta.microServico.repository.CartoesGeradosRepository;
import br.com.zup.beta.microServico.repository.CarteirasPayRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("api/cartoes")
public class CarteiraController {

    private final CarteirasSmPay carteirasSmPay;
    CarteirasPayRepository carteirasPayRepository;
    CartoesGeradosRepository cartoesGeradosRepository;

    @Autowired
    public CarteiraController(CarteirasSmPay carteirasSmPay,
                              CarteirasPayRepository carteirasPayRepository,
                              CartoesGeradosRepository cartoesGeradosRepository) {
        this.carteirasSmPay = carteirasSmPay;
        this.carteirasPayRepository = carteirasPayRepository;
        this.cartoesGeradosRepository = cartoesGeradosRepository;
    }

    @Transactional
    @PostMapping("carteiras/{id}")
    public ResponseEntity<?> associaCarteira(@PathVariable(value = "id") Long id,
                                             @RequestBody @Valid CarteirasPayDto carteirasPayDto){

        Optional<CartoesGerados> findIdCartao = cartoesGeradosRepository.findById(id);
        System.out.println(id);

        //validar id do cartão
        System.out.println(id);
        if (findIdCartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado ");
        }

        try {
        //criando os dados para tabela de carteiras
        CarteirasPay carteirasPay = new CarteirasPay(carteirasPayDto.getEmail(), carteirasPayDto.getCarteira());
        carteirasPayRepository.save(carteirasPay);

            CarteirasSmPay.PayRequest request = new CarteirasSmPay.PayRequest(carteirasPay);
            CarteirasSmPay.PayResponse resposta = carteirasSmPay.carteiraPay(findIdCartao.get().getcartaoId(),
                    request);

            return ResponseEntity.ok().build();
        } catch (FeignException exception) {
            return ResponseEntity.status(exception.status()).build();
        }


//        return ResponseEntity.ok().build();
    }
}
