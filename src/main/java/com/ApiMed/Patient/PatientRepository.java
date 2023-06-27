package com.ApiMed.Patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long>{

    Page<Patient> findAllByAtivoTrue(Pageable paginacao);
    
}

