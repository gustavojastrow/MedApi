package com.ApiMed.domain.Patient;

public record PatientListData(Long id,String nome, String email) {

    public PatientListData(Patient patient){
        this(patient.getId(),patient.getNome(), patient.getEmail());
    }
}