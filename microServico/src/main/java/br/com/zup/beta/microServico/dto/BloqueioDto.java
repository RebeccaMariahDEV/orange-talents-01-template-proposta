package br.com.zup.beta.microServico.dto;

import javax.validation.constraints.NotBlank;

public class BloqueioDto {

    @NotBlank
    private String idcartao;

    @Deprecated
    public BloqueioDto() {
    }

    public @NotBlank String getIdcartao() {
        return idcartao;
    }

}
