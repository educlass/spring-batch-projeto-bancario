package com.udemy.ProjetoBancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.ProjetoBancario.entity.ArquivoProcessadoEntity;

@Repository
public interface ArquivoProcessadoRepository extends JpaRepository<ArquivoProcessadoEntity, Long> {
	
}
