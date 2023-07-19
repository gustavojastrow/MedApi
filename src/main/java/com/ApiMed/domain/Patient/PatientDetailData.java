package com.ApiMed.domain.Patient;

import com.ApiMed.Adress.Adress;

public record PatientDetailData(Long id,String nome,String email,String telefone, Adress endereco) {  


    public PatientDetailData(Patient patient){
        this(patient.getId(),patient.getNome(),patient.getEmail(),patient.getTelefone(),patient.getEndereco());
    }

}
