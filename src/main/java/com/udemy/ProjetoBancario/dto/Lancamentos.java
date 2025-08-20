package com.udemy.ProjetoBancario.dto;


import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Lancamentos {
	
	private String dtLancamento;
	private String descricao;
	private BigDecimal valor;
	private String tipo;

}
