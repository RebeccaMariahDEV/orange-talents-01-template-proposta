package br.com.zup.beta.microServico.dto;

import javax.validation.constraints.NotBlank;

public class CarteirasPayDto {
    @NotBlank
    private String email;

    @NotBlank
    private String carteira;

    public CarteirasPayDto(@NotBlank String email, @NotBlank String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
