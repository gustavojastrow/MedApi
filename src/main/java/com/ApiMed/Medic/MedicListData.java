package com.ApiMed.Medic;

public record MedicListData(Long id,String nome, String email, String crm, Speciality especialidade) {

    public MedicListData(Medic medico){
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
