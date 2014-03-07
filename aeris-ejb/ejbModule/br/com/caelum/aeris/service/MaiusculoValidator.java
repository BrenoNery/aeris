package br.com.caelum.aeris.service;

import org.hibernate.validator.*;

public class MaiusculoValidator implements Validator<Maiusculo> {

	@SuppressWarnings("unused")
	private Maiusculo anotacao;
	
	public void initialize(Maiusculo anotacao) {
		this.anotacao = anotacao;
	}
	
	public boolean isValid(Object value) {
		String asString = value.toString();
		return asString.equals(asString.toUpperCase());
	}
}
