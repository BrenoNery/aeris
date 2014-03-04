package br.com.caelum.aeris.session;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.log.Log;

import br.com.caelum.aeris.entity.Trecho;

@Name("auditoria")
@Scope(ScopeType.EVENT)
public class AuditoriaHandler {

	@Logger
	private Log log;
	
	@Observer("novoTrecho")
	public void registraQueTrechoFoiAdicionado(Trecho trecho) {
		
		log.info("Novo Trecho registrado! #0", trecho);
	}

}
