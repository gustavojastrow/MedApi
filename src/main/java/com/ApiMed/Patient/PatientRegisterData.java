package com.ApiMed.Patient;

import com.ApiMed.Adress.DataAdress;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientRegisterData(
    @NotBlank
    String nome,
    @NotBlank
    @Email
    String email,
    @NotNull
    @Pattern(regexp = "^[0-9]{2}[0-9]{9}")
    String telefone,
    @NotNull @Valid
    DataAdress endereco) { 
}
