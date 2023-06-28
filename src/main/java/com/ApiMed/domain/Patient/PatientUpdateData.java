package com.ApiMed.domain.Patient;
import com.ApiMed.domain.Adress.DataAdress;

import jakarta.validation.constraints.NotNull;


public record PatientUpdateData(
    @NotNull
    Long id,
    String nome, 
    String telefone, 
    DataAdress endereco){
}

