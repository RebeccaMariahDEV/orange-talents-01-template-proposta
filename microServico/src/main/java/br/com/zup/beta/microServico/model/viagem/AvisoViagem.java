package br.com.zup.beta.microServico.model.viagem;

import br.com.zup.beta.microServico.model.cartoes.CartoesGerados;
import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcartao", referencedColumnName = "id")
    private CartoesGerados idcartao;

    @NotBlank
    private String destinoViagem;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate terminoViagem;

    private String userAgent;

    private String ipCliente;

    @Deprecated
    public AvisoViagem(){    }

    public AvisoViagem(@NotBlank String destinoViagem,
                       @NotNull LocalDate terminoViagem, String userAgent,
                       String ipCliente, @NotBlank CartoesGerados idcartao) {
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
        this.userAgent = userAgent;
        this.ipCliente = ipCliente;
        this.idcartao = idcartao;
    }

    public Long getId() {
        return id;
    }

    public CartoesGerados getIdcartao() {
        return idcartao;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIpCliente() {
        return ipCliente;
    }
}
