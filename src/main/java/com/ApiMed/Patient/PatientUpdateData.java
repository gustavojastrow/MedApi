package com.ApiMed.Patient;
import jakarta.validation.constraints.NotNull;

import com.ApiMed.Adress.DataAdress;


public record PatientUpdateData(
    @NotNull
    Long id,
    String nome, 
    String telefone, 
    DataAdress endereco){
}

