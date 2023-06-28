package com.ApiMed.domain.Medic;

import com.ApiMed.domain.Adress.DataAdress;

import jakarta.validation.constraints.NotNull;

public record MedicUpdateData(
    @NotNull
    Long id,
    String nome, 
    String telefone, 
    DataAdress endereco) {

}