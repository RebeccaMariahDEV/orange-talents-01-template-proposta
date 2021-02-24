package br.com.zup.beta.microServico.model.cartoes;

import br.com.zup.beta.microServico.model.proporsta.NovaProposta;
import jdk.jfr.Timestamp;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CartoesGerados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idcartao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "novaPropostaId", referencedColumnName = "id")
    private NovaProposta novaPropostaid;

    @Timestamp
    private LocalDate geradoEm;

    private boolean ativo = true;

    @Deprecated
    public CartoesGerados() {
    }

    public CartoesGerados(String idcartao, NovaProposta novaPropostaid, LocalDate geradoEm) {
        this.idcartao = idcartao;
        this.novaPropostaid = novaPropostaid;
        this.geradoEm = geradoEm;
    }

    public Long getId() {
        return id;
    }

    public String getcartaoId() {
        return idcartao;
    }

    public NovaProposta getNovaPropostaid() {
        return novaPropostaid;
    }

    public LocalDate getGeradoEm() {
        return geradoEm;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
