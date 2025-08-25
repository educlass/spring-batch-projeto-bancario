package com.udemy.ProjetoBancario.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.udemy.ProjetoBancario.config.PathsProperties;
import com.udemy.ProjetoBancario.dto.LancamentoBancarioDto;
import com.udemy.ProjetoBancario.processor.LancamentoBancarioProcessor;
import com.udemy.ProjetoBancario.util.Util;
import com.udemy.ProjetoBancario.wrapper.LancamentoReaderWrapper;


@Configuration
public class LancamenoReaderConfig {
	
	@Autowired
	private PathsProperties pathsProperties;
	
	@Bean
	@StepScope//COLOCAR NO SCOPSTED quando utilizar de recursos dinamicos, aqui no caso o arquivo ainda nao estava disponivel quando o ben foi criado, colocando no scop resolve-se o problema
	MultiResourceItemReader<LancamentoBancarioDto> multiLancamentosReader(
			LancamentoBancarioProcessor processor) {
	    
		MultiResourceItemReader<LancamentoBancarioDto> reader = new MultiResourceItemReader<>();
	    
	    Resource[] resources = Util
	    		.listResources(pathsProperties.getEmProcessamento(), "lancamento_bancario");
	    
	    FlatFileItemReader<LancamentoBancarioDto> delegate = lancamentosReader();
	    LancamentoReaderWrapper wrapper = new LancamentoReaderWrapper(delegate, processor);
	    
	    reader.setDelegate(wrapper);
	    reader.setStrict(false); // evita erro se algum arquivo estiver ausente
	    reader.setName("multiLancamentosReader");
	    reader.setResources(resources);
	    
	    return reader;
	}
	

	private FlatFileItemReader<LancamentoBancarioDto> lancamentosReader(){
		
		return new FlatFileItemReaderBuilder<LancamentoBancarioDto>()
				.name("lancamentosReader")
				.strict(false)
				.linesToSkip(1)//pula primeira linha
				.delimited()
				.names("dtLancamento", "descricao", "valor", "tipo")
				.targetType(LancamentoBancarioDto.class)
				.build();
		
	}

}
