package com.udemy.ProjetoBancario.step;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.udemy.ProjetoBancario.config.PathsProperties;

@Configuration
public class StepPreparaArquivoProcessadoConfig {
	
	@Bean(name = "preparaArquivoProcessadoStep")
	Step preparaArquivoProcessadoStep(JobRepository jobRepository, 
			PlatformTransactionManager platformTransactionManager, Tasklet preparaArquivoProcessadoTasklet) {
		
		return new StepBuilder("preparaArquivoProcessadoStep", jobRepository)
				.tasklet(preparaArquivoProcessadoTasklet, platformTransactionManager)
				.build();
		
	}
	
	@Bean
	List<String> listaArquivos(PathsProperties pathsProperties) {
	    File pasta = new File(pathsProperties.getEntrada());
	    File[] arquivos = pasta.listFiles((dir, name) -> name.startsWith("lancamento_bancario_") && name.endsWith(".csv"));

	    return Arrays.stream(arquivos)
	                 .map(File::getAbsolutePath)
	                 .toList();
	}


}
