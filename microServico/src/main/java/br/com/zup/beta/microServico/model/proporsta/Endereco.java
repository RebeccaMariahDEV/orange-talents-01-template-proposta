package br.com.zup.beta.microServico.model.proporsta;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable //fala que o endereço é da classe de propostas
public class Endereco {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String cep;

    @NotBlank
    private String numero;

    private String complemento;

    public String getLogradouro() {
        return logradouro;
    }

    public String getCep() {
        return cep;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    @Deprecated
    private Endereco(){

    }
}
