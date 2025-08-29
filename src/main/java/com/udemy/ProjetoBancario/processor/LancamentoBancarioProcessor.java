package com.udemy.ProjetoBancario.processor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import com.udemy.ProjetoBancario.dto.LancamentoBancarioDto;
import com.udemy.ProjetoBancario.entity.ArquivoProcessadoEntity;
import com.udemy.ProjetoBancario.entity.LancamentoBancarioEntity;
import com.udemy.ProjetoBancario.repository.ArquivoProcessadoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LancamentoBancarioProcessor implements ItemProcessor<LancamentoBancarioDto, LancamentoBancarioEntity>{

	//INCLUIDO PARA SABER QUAL RESOURCE ESTOU LENDO E AI SABER QUAL ID É DE QUAL ARQUIVO
	private final Map<String, Long> arquivoIdMap;
	private Resource currentResource;
	
	@Autowired
	private ArquivoProcessadoRepository arquivoProcessadoRepository;

	@Override
	public LancamentoBancarioEntity process(LancamentoBancarioDto item) throws Exception {
		
		String nomeArquivo = currentResource.getFilename();
        Long idArquivo = arquivoIdMap.get(nomeArquivo);
        Optional<ArquivoProcessadoEntity> byId = arquivoProcessadoRepository.findById(idArquivo);
        
		LancamentoBancarioEntity entity = new LancamentoBancarioEntity();
        entity.setDescricao(item.getDescricao());
        entity.setValor(item.getValor());
        entity.setTipo(item.getTipo());
        entity.setDataLancamento(LocalDate.parse(item.getDtLancamento(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        entity.setStatus("FINALIZADO");
        entity.setCriadoEm(LocalDateTime.now());
        entity.setArquivo(byId.get());
		
		return entity;
		
	}
	
	public LancamentoBancarioProcessor(Map<String, Long> arquivoIdMap) {
        this.arquivoIdMap = arquivoIdMap;
    }

    public void setResource(Resource resource) {
        this.currentResource = resource;
    }

}
