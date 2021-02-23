package br.com.zup.beta.microServico.model.bloqueio;

import br.com.zup.beta.microServico.model.cartoes.CartoesGerados;
import jdk.jfr.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sistemaResponsavel;

    private String userAgent;

    private String ipCliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcartao", referencedColumnName = "id")
    private CartoesGerados idcartao;
    @Timestamp
    private LocalDate bloqueadoEm;

    private boolean validadorCartao;

}
