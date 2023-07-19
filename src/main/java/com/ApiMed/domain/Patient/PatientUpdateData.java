package com.ApiMed.domain.Patient;
import jakarta.validation.constraints.NotNull;

import com.ApiMed.Adress.DataAdress;


public record PatientUpdateData(
    @NotNull
    Long id,
    String nome, 
    String telefone, 
    DataAdress endereco){
}

