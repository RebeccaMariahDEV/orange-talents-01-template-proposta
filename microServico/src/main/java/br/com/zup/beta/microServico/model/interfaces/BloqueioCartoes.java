package br.com.zup.beta.microServico.model.interfaces;

import br.com.zup.beta.microServico.model.bloqueio.BloqueioCartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bloqueio", url = "http://localhost:8888/api/cartoes")
public interface BloqueioCartoes {

    @PostMapping("{id}/bloqueios")
    BloqueioResponse bloqueiaCartao(@RequestBody BloqueioRequest bloqueioRequest, @RequestParam("id") String id);

    class BloqueioRequest{
        private String sistemaResponsave;

        public String getSistemaResponsave() {
            return sistemaResponsave;
        }

        public BloqueioRequest(BloqueioCartao bloqueioCartao) {
            this.sistemaResponsave = bloqueioCartao.getUserAgent();
        }
    }

    class BloqueioResponse {
        private String resultado;

        public String getResultado() {
            return resultado;
        }

        public BloqueioResponse(String resultado) {
            this.resultado = resultado;
        }
    }


}
