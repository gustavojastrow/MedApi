package com.ApiMed.domain.Medic;

import com.ApiMed.domain.Adress.Adress;

public record MedicDetailData(Long id,String nome,String email,String telefone, String crm, Speciality especialidade, Adress endereco) {  


    public MedicDetailData(Medic medic){
        this(medic.getId(),medic.getNome(),medic.getEmail(),medic.getTelefone(),medic.getCrm(),medic.getEspecialidade(),medic.getEndereco());
    }

}
