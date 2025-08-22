package com.udemy.ProjetoBancario.datasource;

import javax.sql.DataSource;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean
    JobRepository jobRepository(
        @Qualifier("springDataSource") DataSource dataSource,
        @Qualifier("springTransactionManager") PlatformTransactionManager transactionManager
    ) throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(transactionManager);
        factory.setDatabaseType("mysql"); // ou "postgres", etc.
        return factory.getObject();
    }

    @Primary
    @Bean
    PlatformTransactionManager springTransactionManager(
        @Qualifier("springDataSource") DataSource dataSource
    ) {
        return new DataSourceTransactionManager(dataSource);
    }
}

