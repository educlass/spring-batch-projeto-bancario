package com.udemy.ProjetoBancario.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobLancamentosConfig {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Bean
	Job processaLancamentos(Step preparaArquivoProcessadoStep) {
		
		return new JobBuilder("processaLancamentos", jobRepository)
				.start(preparaArquivoProcessadoStep)
				.incrementer(new RunIdIncrementer())
				.build();
		
	}


}
