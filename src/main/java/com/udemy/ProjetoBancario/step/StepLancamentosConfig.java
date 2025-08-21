package com.udemy.ProjetoBancario.step;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.udemy.ProjetoBancario.dto.LancamentoBancarioDto;
import com.udemy.ProjetoBancario.entity.LancamentoBancarioEntity;

@Configuration
public class StepLancamentosConfig {
	
	@Bean
	Step lancamentosStep(JobRepository jobRepository, 
			PlatformTransactionManager platformTransactionManager, FlatFileItemReader<LancamentoBancarioDto> lancamentosReader) {
		
		return new StepBuilder("lancamentosStep", jobRepository)
				.<LancamentoBancarioDto, LancamentoBancarioEntity>chunk(1, platformTransactionManager)
				.reader(lancamentosReader)
				.processor(process())
				.writer(writer())
				.build();
		
	}

	private ItemProcessor<? super LancamentoBancarioDto, ? extends LancamentoBancarioEntity> process() {
		return item ->{
			
			LancamentoBancarioEntity entity = new LancamentoBancarioEntity();
	        entity.setDescricao(item.getDescricao());
	        entity.setValor(item.getValor());
	        entity.setTipo(item.getTipo());
	        entity.setDataLancamento(LocalDate.parse(item.getDtLancamento(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	        entity.setStatus("FINALIZADO");
	        entity.setCriadoEm(LocalDateTime.now());
			
	        
	        System.out.println(entity.toString());
	        
			return entity;
		};
		
	}

	private ItemWriter<? super LancamentoBancarioEntity> writer() {
		return items -> items.forEach(System.out::println);
	}

	


}
