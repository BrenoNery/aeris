package br.com.caelum.aeris.session;

import br.com.caelum.aeris.entity.Trecho;

public interface VooHandler {

	String salvarVoo();
	
	String manipulaVoos(Trecho trecho);
	
	void iniciaVoos();
		
	Trecho getTrechoSelecionado();
	
	void destruicaoObrigatoria();
}
