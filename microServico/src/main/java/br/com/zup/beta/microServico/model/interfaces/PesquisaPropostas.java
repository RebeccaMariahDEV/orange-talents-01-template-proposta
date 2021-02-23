package br.com.zup.beta.microServico.model.interfaces;

import br.com.zup.beta.microServico.model.proporsta.NovaProposta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;


@FeignClient(name = "analise", url = "http://localhost:8888/api/cartoes")
public interface PesquisaPropostas {

    @GetMapping
    AnaliseResponse analiseCartao(@RequestParam("idProposta") Long idProposta);

    class AnaliseRequest{
        private Long idProposta;

        public Long getIdProposta() {
            return idProposta;
        }

        public AnaliseRequest(NovaProposta novaProposta) {
            this.idProposta = novaProposta.getId();
        }
    }

    class AnaliseResponse {
        private String id;
        private LocalDate emitidoEm;
        private String titular;
        private ArrayList bloqueios;
        private ArrayList avisos;
        private ArrayList carteiras;
        private ArrayList parcelas;
        private BigDecimal limite;
        private Map renegociacao;
        private Map vencimento;
        private Long idProposta;

        public AnaliseResponse(String id, LocalDate emitidoEm, String titular, ArrayList bloqueios, ArrayList avisos,
                               ArrayList carteiras, ArrayList parcelas,
                               BigDecimal limite, Map renegociacao, Map vencimento, Long idProposta) {
            this.id = id;
            this.emitidoEm = emitidoEm;
            this.titular = titular;
            this.bloqueios = bloqueios;
            this.avisos = avisos;
            this.carteiras = carteiras;
            this.parcelas = parcelas;
            this.limite = limite;
            this.renegociacao = renegociacao;
            this.vencimento = vencimento;
            this.idProposta = idProposta;
        }

        public String getId() {
            return id;
        }

        public LocalDate getEmitidoEm() {
            return emitidoEm;
        }

        public String getTitular() {
            return titular;
        }

        public ArrayList getBloqueios() {
            return bloqueios;
        }

        public ArrayList getAvisos() {
            return avisos;
        }

        public ArrayList getCarteiras() {
            return carteiras;
        }

        public ArrayList getParcelas() {
            return parcelas;
        }

        public BigDecimal getLimite() {
            return limite;
        }

        public Map getRenegociacao() {
            return renegociacao;
        }

        public Map getVencimento() {
            return vencimento;
        }

        public Long getIdProposta() {
            return idProposta;
        }
    }



}
