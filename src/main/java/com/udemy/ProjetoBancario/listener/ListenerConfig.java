package com.udemy.ProjetoBancario.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udemy.ProjetoBancario.service.GlobalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ListenerConfig implements StepExecutionListener, JobExecutionListener{
	
	@Autowired
	private GlobalService service;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("Iniciando processamento de arquivos");
		JobExecutionListener.super.beforeJob(jobExecution);
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("Processamento Finalizado");
		JobExecutionListener.super.afterJob(jobExecution);
	}
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		StepExecutionListener.super.beforeStep(stepExecution);
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		
		service.moverArquivos();
		log.info("Arquivos movidos para Finalizado com sucesso");
		
		return StepExecutionListener.super.afterStep(stepExecution);
	}
	
}
