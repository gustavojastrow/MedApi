package com.ApiMed.Medic;


import com.ApiMed.Adress.Adress;

import jakarta.persistence.Id;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Table(name="medics")
@Entity(name="Medic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medic {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Speciality especialidade;
    @Embedded
    private Adress endereco;

    private Boolean ativo;

    public Medic(MedicRegisterData dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Adress(dados.endereco());
        this.ativo = true;
    }

    public void updateInfo(MedicUpdateData dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco()!= null){
            this.endereco.updateInfo(dados.endereco());
        }

    }

    public void exclude() {
        this.ativo = false;
    }
}


