package com.udemy.ProjetoBancario.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.udemy.ProjetoBancario.config.PathsProperties;
import com.udemy.ProjetoBancario.dto.Lancamentos;

@Configuration
public class LancamenoReaderConfig {
	
	@Bean
	FlatFileItemReader<Lancamentos> lancamentosReader(PathsProperties pathsProperties){
		
		return new FlatFileItemReaderBuilder<Lancamentos>()
				.name("lancamentosReader")
				.linesToSkip(1)//pula primeira linha
				.resource(new FileSystemResource(pathsProperties.getLancamentos()))
				.delimited()
				.names("dtLancamento", "descricao", "valor", "tipo")
				.targetType(Lancamentos.class)
				.build();
		
	}

}
