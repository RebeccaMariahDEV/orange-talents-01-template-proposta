package br.com.zup.beta.microServico.dto;

import javax.validation.constraints.NotBlank;

public class BiometriaDto {

    @NotBlank
    private String biometria;

    public String getBiometria() {
        return biometria;
    }

}
