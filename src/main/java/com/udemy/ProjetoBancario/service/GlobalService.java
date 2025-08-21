package com.udemy.ProjetoBancario.service;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.ProjetoBancario.entity.ArquivoProcessadoEntity;
import com.udemy.ProjetoBancario.repository.ArquivoProcessadoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GlobalService {
	
	@Autowired
	private ArquivoProcessadoRepository arquivoProcessadoRepository;
	
	public ArquivoProcessadoEntity processaArquivo(String item) {
		
		ArquivoProcessadoEntity entity =  new ArquivoProcessadoEntity();
		
		//lancamento_bancario_20250820_CNAB.cvs
		String name = Paths.get(item).toFile().getName();
		String[] partes = name.replace(".csv", "").split("_");
		LocalDate data = LocalDate.parse(partes[2], DateTimeFormatter.ofPattern("yyyyMMdd"));
        String tipo = partes[3];
		
		entity.setNomeArquivo(name);
		entity.setDataRecebimento(data.atStartOfDay());
		entity.setStatus("EM_PROCESSAMENTO");
		entity.setTipoArquivo(tipo);
		entity.setCriadoEm(LocalDateTime.now());
		
		log.info(entity.toString());
		
		//Falta mover arquivo
		
		return arquivoProcessadoRepository.save(entity);
	}

}
