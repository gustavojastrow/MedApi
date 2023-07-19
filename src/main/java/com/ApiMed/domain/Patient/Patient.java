package com.ApiMed.domain.Patient;

import com.ApiMed.Adress.Adress;

import jakarta.persistence.Id;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Table(name="patients")
@Entity(name="Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Patient {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    @Embedded
    private Adress endereco;

    private Boolean ativo;

    public Patient(PatientRegisterData dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Adress(dados.endereco());
        this.ativo = true;
    }

    public void updateInfo(PatientUpdateData dados) {
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


