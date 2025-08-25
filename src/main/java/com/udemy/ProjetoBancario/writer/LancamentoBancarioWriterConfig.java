package com.udemy.ProjetoBancario.writer;

import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.udemy.ProjetoBancario.entity.LancamentoBancarioEntity;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class LancamentoBancarioWriterConfig {
	
	@Bean(name = "lancamentoBancarioWriter")
	JpaItemWriter<LancamentoBancarioEntity> jpaItemWriter(EntityManagerFactory entityManagerFactory) {
	    JpaItemWriter<LancamentoBancarioEntity> writer = new JpaItemWriter<>();
	    writer.setEntityManagerFactory(entityManagerFactory);
	    return writer;
	}


}
