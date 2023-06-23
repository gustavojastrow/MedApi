package com.ApiMed.Medic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicRepository extends JpaRepository<Medic, Long>{

    Page<Medic> findAllByAtivoTrue(Pageable paginacao);
    
}
