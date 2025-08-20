package com.udemy.ProjetoBancario.entity;

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
@Table(name = "erro_lancamento")
@Getter
@Setter
public class ErroLancamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_lancamento")
    private LancamentoBancarioEntity lancamento;

    @Column(name = "motivo", columnDefinition = "TEXT")
    private String motivo;

    @Column(name = "registrado_em")
    private LocalDateTime registradoEm;
}

