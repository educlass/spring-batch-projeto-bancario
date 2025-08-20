package com.udemy.ProjetoBancario.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ConfigurationProperties(prefix = "app.paths.arquivos")
@Component
public class PathsProperties {
	
	private String lancamentos;
    private String relatorios;

}
