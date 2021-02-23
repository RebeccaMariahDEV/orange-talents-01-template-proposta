package br.com.zup.beta.microServico.model.biometria;

import br.com.zup.beta.microServico.model.cartoes.CartoesGerados;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String biometria;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcartao", referencedColumnName = "id")
    private CartoesGerados idCartao;

    @CreationTimestamp
    private LocalDate criadoEm;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public void setBiometria(String biometria, CartoesGerados idCartao) {
        this.biometria = biometria;
        this.idCartao = idCartao;
    }

    public boolean estaEmBase64() {
        return Base64.isBase64(biometria.getBytes());
    }

    public Long getId() {
        return id;
    }

    public String getBiometria() {
        return biometria;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public CartoesGerados getIdCartao() {
        return idCartao;
    }


}
