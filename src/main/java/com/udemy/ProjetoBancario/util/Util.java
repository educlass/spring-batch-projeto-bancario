package com.udemy.ProjetoBancario.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class Util {
	
	public static List<String> listFiles(String path, String fileName) {
	    File pasta = new File(path);
	    File[] arquivos = pasta.listFiles((dir, name) -> name.startsWith("lancamento_bancario_"));

	    return Arrays.stream(arquivos)
	                 .map(File::getAbsolutePath)
	                 .toList();
	}
	
	public static Resource[] listResources(String path, String fileName) {
	    File pasta = new File(path);
	    File[] arquivos = pasta.listFiles((dir, name) -> name.startsWith("lancamento_bancario_"));

	    return Arrays.stream(arquivos)
	                 .map(FileSystemResource::new)
	                 .toArray(Resource[]::new);
	}

}
