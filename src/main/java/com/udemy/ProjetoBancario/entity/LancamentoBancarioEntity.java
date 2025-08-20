package com.udemy.ProjetoBancario.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lancamento_bancario")
@Getter
@Setter
public class LancamentoBancarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_arquivo")
    private ArquivoProcessadoEntity arquivo;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "status")
    private String status;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    
}