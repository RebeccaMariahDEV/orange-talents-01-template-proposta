package br.com.zup.beta.microServico.model;

import br.com.zup.beta.microServico.core.validador.CpfCnpj;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Entity
public class NovaProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String endereco;

    @NotNull
    @PositiveOrZero
    private Double salario;

    @NotNull
    @CpfCnpj
    @Column(unique = true, nullable = false)
    private String documentoId;

    @CreationTimestamp
    private LocalDate criadoEm;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getSalario() {
        return salario;
    }

    public String getDocumento_id() {
        return documentoId;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }


    public void setProposta(String nome, String sobrenome, String email, String endereco, Double salario,
                            String documentoId) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
        this.documentoId = documentoId;
    }
}
