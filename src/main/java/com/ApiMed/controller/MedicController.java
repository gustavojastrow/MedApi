package com.ApiMed.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiMed.Medic.Medic;
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
    public void register(@RequestBody @Valid MedicRegisterData dados){
        repository.save(new Medic(dados));
    }

    @GetMapping
    public Page<MedicListData> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(MedicListData::new);
    }
    
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody MedicUpdateData dados){
        var medico = repository.getReferenceById(dados.id());
        medico.updateInfo(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.exclude();
    }
}
