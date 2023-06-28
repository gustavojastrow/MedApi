package com.ApiMed.domain.Adress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataAdress(
    @NotBlank
    String logradouro, 
    @NotBlank
    String bairro,
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    String cep,
    @NotBlank
    String cidade, 
    @NotBlank
    String uf, 
    String complemento, 
    String numero) {
}
