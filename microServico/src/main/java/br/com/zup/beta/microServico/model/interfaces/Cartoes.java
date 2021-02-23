package br.com.zup.beta.microServico.model.interfaces;

import br.com.zup.beta.microServico.model.proporsta.NovaProposta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "cartao", url = "http://localhost:8888/api/cartoes")
public interface Cartoes {

    @PostMapping
    NovoCartaoResponse novoCartao(@RequestBody NovoCartaoRequest novoCartaoRequest);

    class NovoCartaoRequest{

        private Long idProposta;
        private String documento;
        private String nome;


        public Long getIdProposta() {
            return idProposta;
        }

        public String getDocumento() {
            return documento;
        }

        public String getNome() {
            return nome;
        }

        public NovoCartaoRequest(NovaProposta novaProposta) {
            this.idProposta = novaProposta.getId();
            this.documento = novaProposta.getDocumentoId();
            this.nome = novaProposta.getNome();
        }
    }

    class NovoCartaoResponse{

//        private String id;
//        private LocalDate emitidoEm;
//        private String titular;
//        private ArrayList bloqueios;
//        private ArrayList avisos;
//        private ArrayList carteiras;
//        private ArrayList parcelas;
//        private BigDecimal limite;
//        private Map renegociacao;
//        private Map vencimento;
//        private Long idProposta;
//
//        public NovoCartaoResponse(String id, LocalDate emitidoEm, String titular, ArrayList bloqueios,
//                                  ArrayList avisos, ArrayList carteiras, ArrayList parcelas, BigDecimal limite,
//                                  Map renegociacao, Map vencimento, Long idProposta) {
//            this.id = id;
//            this.emitidoEm = emitidoEm;
//            this.titular = titular;
//            this.bloqueios = bloqueios;
//            this.avisos = avisos;
//            this.carteiras = carteiras;
//            this.parcelas = parcelas;
//            this.limite = limite;
//            this.renegociacao = renegociacao;
//            this.vencimento = vencimento;
//            this.idProposta = idProposta;
//        }
//
//
//        public String getId() {
//            return id;
//        }
//
//        public LocalDate getEmitidoEm() {
//            return emitidoEm;
//        }
//
//        public String getTitular() {
//            return titular;
//        }
//
//        public ArrayList getBloqueios() {
//            return bloqueios;
//        }
//
//        public ArrayList getAvisos() {
//            return avisos;
//        }
//
//        public ArrayList getCarteiras() {
//            return carteiras;
//        }
//
//        public ArrayList getParcelas() {
//            return parcelas;
//        }
//
//        public BigDecimal getLimite() {
//            return limite;
//        }
//
//        public Map getRenegociacao() {
//            return renegociacao;
//        }
//
//        public Map getVencimento() {
//            return vencimento;
//        }
//
//        public Long getIdProposta() {
//            return idProposta;
//        }
//
//        @Override
//        public String toString() {
//            return "NovoCartaoResponse{" +
//                    "id='" + id + '\'' +
//                    ", emitidoEm=" + emitidoEm +
//                    ", titular='" + titular + '\'' +
//                    ", bloqueios=" + bloqueios +
//                    ", avisos=" + avisos +
//                    ", carteiras=" + carteiras +
//                    ", parcelas=" + parcelas +
//                    ", limite=" + limite +
//                    ", renegociacao=" + renegociacao +
//                    ", vencimento=" + vencimento +
//                    ", idProposta=" + idProposta +
//                    '}';
//        }
    }


}
