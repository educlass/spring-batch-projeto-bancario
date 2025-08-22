package com.udemy.ProjetoBancario.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.udemy.ProjetoBancario.repository", // ajuste pro seu pacote
    entityManagerFactoryRef = "appEntityManagerFactory",
    transactionManagerRef = "appTransactionManager"
)
public class AppDataSourceConfig {

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean appEntityManagerFactory(
        @Qualifier("appDataSource") DataSource dataSource,
        EntityManagerFactoryBuilder builder
    ) {
    	
    	Map<String, Object> props = new HashMap<>();
    	props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	
        return builder
            .dataSource(dataSource)
            .packages("com.udemy.ProjetoBancario.entity") // ajuste pro seu pacote de entidades
            .persistenceUnit("appPU")
            .properties(props)
            .build();
    }

    @Bean
    PlatformTransactionManager appTransactionManager(
        @Qualifier("appEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

