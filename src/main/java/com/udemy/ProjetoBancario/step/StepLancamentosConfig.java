package com.udemy.ProjetoBancario.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.udemy.ProjetoBancario.config.PathsProperties;
import com.udemy.ProjetoBancario.dto.LancamentoBancarioDto;
import com.udemy.ProjetoBancario.entity.LancamentoBancarioEntity;
import com.udemy.ProjetoBancario.processor.LancamentoBancarioProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StepLancamentosConfig {
	
	@Bean(name = "lancamentosStep")
	Step lancamentosStep(JobRepository jobRepository, 
			@Qualifier("appTransactionManager") PlatformTransactionManager platformTransactionManager,
			PathsProperties pathsProperties, MultiResourceItemReader<LancamentoBancarioDto> multiLancamentosReader,
			LancamentoBancarioProcessor processor,
			JpaItemWriter<LancamentoBancarioEntity> lancamentoBancarioWriter) {
		
		return new StepBuilder("lancamentosStep", jobRepository)
				.<LancamentoBancarioDto, LancamentoBancarioEntity>chunk(1, platformTransactionManager)
				.reader(multiLancamentosReader)
				.processor(processor)
				.writer(lancamentoBancarioWriter)
				.build();
		
	}

	private ItemWriter<? super LancamentoBancarioEntity> writer() {
		return items -> items.forEach(System.out::println);
	}

	


}
