package com.udemy.ProjetoBancario.processor;

import java.util.Map;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LancamentoBancarioProcessorConfig {
	
	@Bean
    @StepScope
    LancamentoBancarioProcessor lancamentoBancarioProcessor(
            @Value("#{jobExecutionContext['arquivoIdMap']}") Map<String, Long> arquivoIdMap) {
        return new LancamentoBancarioProcessor(arquivoIdMap);
    }

}
