package br.com.curso.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {

	@Getter
	private List<String> erros;

	public ApiErrors(String msgErro) {
		this.erros = Arrays.asList(msgErro);
	}
}
