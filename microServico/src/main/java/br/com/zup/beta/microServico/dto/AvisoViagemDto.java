package br.com.zup.beta.microServico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemDto {

    @NotBlank
    private String destinoViagem;

    @NotNull
    private LocalDate terminoViagem;

    private String userAgent;

    private String ipCliente;

    @Deprecated
    public AvisoViagemDto(){}

    public AvisoViagemDto(@NotBlank String destinoViagem,
                          @NotNull LocalDate terminoViagem) {
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }
}
