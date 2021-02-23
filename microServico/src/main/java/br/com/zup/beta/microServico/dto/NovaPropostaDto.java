package br.com.zup.beta.microServico.dto;

import br.com.zup.beta.microServico.core.validador.CpfCnpj;
import br.com.zup.beta.microServico.model.proporsta.Endereco;

import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class NovaPropostaDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @Email
    @NotBlank
    private String email;

    @Embedded
    @NotNull
    private Endereco endereco;

    @NotNull
    @PositiveOrZero
    private BigDecimal salario;

    @NotNull
    @CpfCnpj
    private String documentoId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(String documentoId) {
        this.documentoId = documentoId;
    }
}

