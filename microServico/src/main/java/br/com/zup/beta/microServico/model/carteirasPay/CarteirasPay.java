package br.com.zup.beta.microServico.model.carteirasPay;

import br.com.zup.beta.microServico.model.cartoes.CartoesGerados;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class CarteirasPay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcartao", referencedColumnName = "id")
    private CartoesGerados idcartao;

    @Email
    @Column(unique = true)
    @NotBlank
    private String email;

    @NotBlank
    private String carteira;

    @CreationTimestamp
    private LocalDate criadoEm;

    @UpdateTimestamp
    private LocalDate atualizadoEm;

    @Deprecated
    public CarteirasPay(){}

    public CarteirasPay(@Email String email, String carteira) {
                this.email = email;
        this.carteira = carteira;
    }

    public Long getId() {
        return id;
    }

    public CartoesGerados getIdcartao() {
        return idcartao;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
