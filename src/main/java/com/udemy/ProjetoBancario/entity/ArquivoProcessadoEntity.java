package com.udemy.ProjetoBancario.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "arquivo_processado")
public class ArquivoProcessadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_arquivo", nullable = false)
    private String nomeArquivo;

    @Column(name = "tipo_arquivo")
    private String tipoArquivo;

    @Column(name = "data_recebimento", nullable = false)
    private LocalDateTime dataRecebimento;

    @Column(name = "status")
    private String status;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
}

