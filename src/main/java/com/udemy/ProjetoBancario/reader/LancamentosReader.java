package com.udemy.ProjetoBancario.reader;

import java.util.List;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.core.io.FileSystemResource;

import com.udemy.ProjetoBancario.dto.LancamentoBancarioDto;

public class LancamentosReader {

	public static FlatFileItemReader<LancamentoBancarioDto> reader(List<String> processamento){
		
		return new FlatFileItemReaderBuilder<LancamentoBancarioDto>()
		        .name("lancamentosReader")
		        .linesToSkip(1)
		        .resource(new FileSystemResource("/home/edu/Documentos/Desenvolvimento/estudos/dados/entrada/emProcessamento/lancamento_bancario_20250820_CNAB.csv"))
		        .strict(false)//elimina a verificação do arquivo de leitura na inicializacao do bean
		        .delimited()
		        .names("dtLancamento", "descricao", "valor", "tipo")
		        .targetType(LancamentoBancarioDto.class)
		        .build();
		
	}
	

}
