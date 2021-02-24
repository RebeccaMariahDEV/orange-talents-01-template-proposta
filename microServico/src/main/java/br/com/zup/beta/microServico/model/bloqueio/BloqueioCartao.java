package br.com.zup.beta.microServico.model.bloqueio;

import br.com.zup.beta.microServico.model.cartoes.CartoesGerados;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userAgent;

    private String ipCliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcartao", referencedColumnName = "idcartao")
    private CartoesGerados idcartao;

    @CreationTimestamp
    private LocalDateTime bloqueadoEm;

    @Deprecated
    public BloqueioCartao(){

    }

    public BloqueioCartao( String userAgent, String ipCliente, CartoesGerados idcartao) {
        this.userAgent = userAgent;
        this.ipCliente = ipCliente;
        this.idcartao = idcartao;
    }

    public Long getId() {
        return id;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public CartoesGerados getIdcartao() {
        return idcartao;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }
}
