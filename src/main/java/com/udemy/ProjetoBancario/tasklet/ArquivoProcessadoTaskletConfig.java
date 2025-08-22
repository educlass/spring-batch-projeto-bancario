package com.udemy.ProjetoBancario.tasklet;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udemy.ProjetoBancario.entity.ArquivoProcessadoEntity;
import com.udemy.ProjetoBancario.service.GlobalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ArquivoProcessadoTaskletConfig {
	
	@Autowired
	private GlobalService globalService;
	
	@Bean
	Tasklet preparaArquivoProcessadoTasklet(List<String> listaArquivos) {
		
		return new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				
				
				
				listaArquivos.forEach(item -> {
					
					ArquivoProcessadoEntity processaArquivo = globalService.processaArquivo(item);
					
					log.info("ENTITY SALVA {}", processaArquivo);
					
					
				});
				
				return RepeatStatus.FINISHED;
			}
		};
		
	}

}
