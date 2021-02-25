package br.com.zup.beta.microServico.model.proporsta;

import br.com.zup.beta.microServico.core.config.segurança.CryptoConverter;
import br.com.zup.beta.microServico.core.validador.CpfCnpj;
import br.com.zup.beta.microServico.model.Status;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
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

    @NotNull
    @Embedded
    private Endereco endereco;

    @NotNull
    @PositiveOrZero
    private BigDecimal salario;

    @NotBlank
    @CpfCnpj
    @Column(unique = true, nullable = false)
    @Convert(converter = CryptoConverter.class)
    private String documentoId;

    @CreationTimestamp
    private LocalDate criadoEm;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean processado = false;



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

    public Endereco getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getDocumentoId() {
        return documentoId;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public boolean isProcessado() {
        return processado;
    }

    public Status getStatus(String resultadoSolicitacao) {
        return Status.resultado(resultadoSolicitacao);
    }

    public void setProposta(String nome, String sobrenome, String email, Endereco endereco, BigDecimal salario,
                            String documentoId) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
        this.documentoId = documentoId;
    }

    public void atualizaStatus(String resultadoSolicitacao) {
        this.status = Status.resultado(resultadoSolicitacao);
    }

    public void setProcessado(){
        this.processado = true;
    }


}
