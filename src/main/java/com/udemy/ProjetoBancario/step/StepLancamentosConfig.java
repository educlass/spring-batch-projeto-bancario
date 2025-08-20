package com.udemy.ProjetoBancario.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.udemy.ProjetoBancario.dto.Lancamentos;

@Configuration
public class StepLancamentosConfig {
	
	@Bean
	Step lancamentosStep(JobRepository jobRepository, 
			PlatformTransactionManager platformTransactionManager, FlatFileItemReader<Lancamentos> lancamentosReader) {
		
		return new StepBuilder("lancamentosStep", jobRepository)
				.<Lancamentos, Lancamentos>chunk(1, platformTransactionManager)
				.reader(lancamentosReader)
				.processor(process())
				.writer(writer())
				.build();
		
	}

	private ItemProcessor<? super Lancamentos, ? extends Lancamentos> process() {
		return item ->{
			System.out.println("VEIO DO PROCESSOR"+item.toString());
			return item;
		};
		
	}

	private ItemWriter<? super Lancamentos> writer() {
		return items -> items.forEach(System.out::println);
	}

	


}
