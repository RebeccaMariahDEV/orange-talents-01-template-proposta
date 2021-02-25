package br.com.zup.beta.microServico.model.interfaces;

import br.com.zup.beta.microServico.model.viagem.AvisoViagem;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "avisos", url = "http://localhost:8888/api/cartoes")
public interface AvisosViagens {

    @PostMapping("{id}/avisos")
    AvisoResponse avisos(@RequestBody AvisoRequest avisoRequest, @RequestParam(value = "id") String id);
//    AvisoResponse avisos(@RequestBody AvisoRequest avisoRequest, @RequestParam("id") String id);

    class AvisoRequest{

        private String destino;
        private LocalDate validoAte;

        public String getDestino() {
            return destino;
        }

        public LocalDate getValidoAte() {
            return validoAte;
        }


        public AvisoRequest(AvisoViagem avisoViagem) {
            this.destino = avisoViagem.getDestinoViagem();
            this.validoAte = avisoViagem.getTerminoViagem();
        }
    }

    class AvisoResponse {

        private String resultado;

        @JsonCreator //deserializa o json
        public AvisoResponse(String resultado) {
            this.resultado = resultado;
        }

        public String getResultado() {
            return resultado;
        }
    }

}
