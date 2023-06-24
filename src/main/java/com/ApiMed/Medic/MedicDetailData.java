package com.ApiMed.Medic;

import com.ApiMed.Adress.Adress;

public record MedicDetailData(Long id,String nome,String email,String telefone, String crm, Speciality especialidade, Adress endereco) {  


    public MedicDetailData(Medic medic){
        this(medic.getId(),medic.getNome(),medic.getEmail(),medic.getTelefone(),medic.getCrm(),medic.getEspecialidade(),medic.getEndereco());
    }

}
