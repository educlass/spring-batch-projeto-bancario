package com.udemy.ProjetoBancario.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.ProjetoBancario.config.PathsProperties;
import com.udemy.ProjetoBancario.entity.ArquivoProcessadoEntity;
import com.udemy.ProjetoBancario.repository.ArquivoProcessadoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GlobalService {
	
	@Autowired
	private ArquivoProcessadoRepository arquivoProcessadoRepository;
	
	@Autowired
	private PathsProperties pathsProperties;
	
	@Transactional(rollbackOn = {Exception.class})
	public ArquivoProcessadoEntity processaArquivo(String item) {
		
		log.info("Inicio processamento do arquivo ", item);
		
		ArquivoProcessadoEntity entity =  new ArquivoProcessadoEntity();
		
		//lancamento_bancario_20250820_CNAB.cvs
		Path path = Paths.get(item);
		String name = path.toFile().getName();
		String[] partes = name.replace(".csv", "").split("_");
		LocalDate data = LocalDate.parse(partes[2], DateTimeFormatter.ofPattern("yyyyMMdd"));
        String tipo = partes[3];
		
		entity.setNomeArquivo(name);
		entity.setDataRecebimento(data.atStartOfDay());
		entity.setStatus("EM_PROCESSAMENTO");
		entity.setTipoArquivo(tipo);
		entity.setCriadoEm(LocalDateTime.now());
		
		
		log.info("Movendo arquivo para processamento");
		try {
			Files.move(path, Paths.get(pathsProperties.getEmProcessamento()+File.separator+name), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.error("Erro", e);
		}
		
		return arquivoProcessadoRepository.save(entity);
	}
	
	public void moverArquivos() {
	    Path origem = Paths.get(pathsProperties.getEmProcessamento());
	    Path destino = Paths.get(pathsProperties.getFinalizado());

	    try (Stream<Path> arquivos = Files.list(origem)) {
	        for (Path arquivo : (Iterable<Path>) arquivos::iterator) {
	            if (Files.isRegularFile(arquivo)) {
	                Path destinoFinal = destino.resolve(arquivo.getFileName());
	                Files.move(arquivo, destinoFinal, StandardCopyOption.REPLACE_EXISTING);
	                log.info("Movido: {}", arquivo.getFileName());
	            }
	        }
	    } catch (IOException e) {
	        log.error("Erro ao mover arquivos: {}", e.getMessage(), e);
	    }
	}


}
