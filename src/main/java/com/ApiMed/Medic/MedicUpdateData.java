package com.ApiMed.Medic;

import com.ApiMed.Adress.DataAdress;

public record MedicUpdateData(Long id,String nome, String telefone, DataAdress endereco) {

}