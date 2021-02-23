package br.com.zup.beta.microServico.model.interfaces;

import br.com.zup.beta.microServico.model.proporsta.NovaProposta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseCliente", url = "http://localhost:9999/api/solicitacao")
public interface AnaliseClient {

    @PostMapping
    ConsultaStatusResponse consulta(@RequestBody ConsultaStatusRequest consultaStatusRequest);

    class ConsultaStatusRequest{
        private Long idProposta;
        private String documento;
        private String nome;

        public ConsultaStatusRequest(NovaProposta novaProposta) {
            this.idProposta = novaProposta.getId();
            this.documento = novaProposta.getDocumentoId();
            this.nome = novaProposta.getNome();
        }

        public Long getIdProposta() {
            return idProposta;
        }

        public String getDocumento() {
            return documento;
        }

        public String getNome() {
            return nome;
        }
    }

    class  ConsultaStatusResponse {

        private String resultadoSolicitacao;
        private Long idProposta;
        private String documento;
        private String nome;

        public ConsultaStatusResponse(String resultadoSolicitacao, Long idProposta, String documento, String nome) {
            this.resultadoSolicitacao = resultadoSolicitacao;
            this.idProposta = idProposta;
            this.documento = documento;
            this.nome = nome;
        }

        public String getResultadoSolicitacao() {
            return resultadoSolicitacao;
        }

        public Long getIdProposta() {
            return idProposta;
        }

        public String getDocumento() {
            return documento;
        }

        public String getNome() {
            return nome;
        }

    }



}