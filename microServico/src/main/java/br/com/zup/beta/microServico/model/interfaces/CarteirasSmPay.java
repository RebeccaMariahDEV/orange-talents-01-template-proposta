package br.com.zup.beta.microServico.model.interfaces;

import br.com.zup.beta.microServico.model.carteirasPay.CarteirasPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "carteiras", url = "http://localhost:8888/api/cartoes")
public interface CarteirasSmPay {

    @PostMapping("{id}/carteiras")
    PayResponse carteiraPay(@RequestParam("id") String id, @RequestBody PayRequest payRequest);

    class PayRequest{
        private String email;
        private String carteira;

        public PayRequest(CarteirasPay carteirasPay) {
            this.email = carteirasPay.getEmail();
            this.carteira = carteirasPay.getCarteira();
        }
    }

    class PayResponse{
        private String resultado;
        private String id;

        public PayResponse(String resultado, String id) {
            this.resultado = resultado;
            this.id = id;
        }

        public String getResultado() {
            return resultado;
        }

        public String getId() {
            return id;
        }
    }

}
