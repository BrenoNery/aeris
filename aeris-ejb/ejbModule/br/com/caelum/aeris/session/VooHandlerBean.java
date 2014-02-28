package br.com.caelum.aeris.session;

import javax.ejb.Local;
/*
 * Import referente a anota��o @Remove que anota um ou mais m�todos respons�veis por representar o fim
 * do ciclo de vida do meu EJB.
 */
import javax.ejb.Remove;
/*
 * Import referente ao container da especifica��o EJB - Stateful Session Bean.
 * Neste caso estou utilizando um EJB como Managed Bean (O Seam permite essa f�cil integra��o etre JSF e EJB).
 */
import javax.ejb.Stateful;

import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope; 
import org.jboss.seam.log.Log;
/*
 * Quando o Seam "decide" que deve encerrar o EJB, o m�todo anotado com @Destroy ser� chamado 
 */
import org.jboss.seam.annotations.Destroy;

import br.com.caelum.aeris.entity.Trecho;


@Stateful

@Local(VooHandler.class)
@Name("vooHandler")
@Scope(ScopeType.SESSION)
public class VooHandlerBean implements VooHandler {

	@Logger
	private Log log;
	
	private Trecho trechoSelecionado;
	
	public String manipulaVoos(Trecho trecho) {
		this.trechoSelecionado = trecho;
		log.info("Trecho selecionado: #0", this.trechoSelecionado);
		return "/voos.xhtml";
	}
	
	public Trecho getTrechoSelecionado() {
		return this.trechoSelecionado;
	}
	
	@Remove
	@Destroy
	public void destruicaoObrigatoria() {
		log.info("Chamando m�todo de destrui��o obrigat�ria");
	}
}
