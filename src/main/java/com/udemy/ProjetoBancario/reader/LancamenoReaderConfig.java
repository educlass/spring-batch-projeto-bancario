package com.udemy.ProjetoBancario.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.udemy.ProjetoBancario.config.PathsProperties;
import com.udemy.ProjetoBancario.dto.LancamentoBancarioDto;

@Configuration
public class LancamenoReaderConfig {
	
	@Bean
	FlatFileItemReader<LancamentoBancarioDto> lancamentosReader(PathsProperties pathsProperties){
		
		return new FlatFileItemReaderBuilder<LancamentoBancarioDto>()
				.name("lancamentosReader")
				.linesToSkip(1)//pula primeira linha
				.resource(new FileSystemResource(pathsProperties.getEmProcessamento()))
				.delimited()
				.names("dtLancamento", "descricao", "valor", "tipo")
				.targetType(LancamentoBancarioDto.class)
				.build();
		
	}

}
