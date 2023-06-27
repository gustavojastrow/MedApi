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

import com.ApiMed.Medic.Medic;
import com.ApiMed.Medic.MedicDetailData;
import com.ApiMed.Medic.MedicListData;
import com.ApiMed.Medic.MedicRegisterData;
import com.ApiMed.Medic.MedicRepository;
import com.ApiMed.Medic.MedicUpdateData;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("medics")
public class MedicController{

    @Autowired
    private MedicRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity register(@RequestBody @Valid MedicRegisterData dados,UriComponentsBuilder uriBuilder){
        var medic = new Medic(dados);
        repository.save(medic);
        
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medic.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicDetailData(medic));
    }

    @GetMapping
    public ResponseEntity<Page<MedicListData>> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(MedicListData::new);
        return ResponseEntity.ok(page);
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody MedicUpdateData dados){
        var medic = repository.getReferenceById(dados.id());
        medic.updateInfo(dados);
        
        return ResponseEntity.ok(new MedicDetailData(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var medic = repository.getReferenceById(id);
        medic.exclude();

        return ResponseEntity.noContent().build();
    }
}
