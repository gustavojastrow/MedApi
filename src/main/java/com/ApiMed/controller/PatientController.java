package com.ApiMed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ApiMed.Patient.Patient;
import com.ApiMed.Patient.PatientDetailData;
import com.ApiMed.Patient.PatientListData;
import com.ApiMed.Patient.PatientRegisterData;
import com.ApiMed.Patient.PatientRepository;
import com.ApiMed.Patient.PatientUpdateData;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("patients")
public class PatientController{

    @Autowired
    private PatientRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity register(@RequestBody @Valid PatientRegisterData dados,UriComponentsBuilder uriBuilder){
        var patient = new Patient(dados);
        repository.save(patient);
        
        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListData>> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(PatientListData::new);
        return ResponseEntity.ok(page);
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody PatientUpdateData dados){
        var patient = repository.getReferenceById(dados.id());
        patient.updateInfo(dados);
        
        return ResponseEntity.ok(new PatientDetailData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        patient.exclude();

        return ResponseEntity.noContent().build();
    }
}
