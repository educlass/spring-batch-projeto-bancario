package com.udemy.ProjetoBancario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "log_execucao_batch")
@Getter
@Setter
public class LogExecucaoBatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_job")
    private String nomeJob;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @Column(name = "status")
    private String status;

    @Column(name = "registros_processados")
    private Integer registrosProcessados;

    @Column(name = "registros_com_erro")
    private Integer registrosComErro;

    @Column(name = "mensagem", columnDefinition = "TEXT")
    private String mensagem;
}

