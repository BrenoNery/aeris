package br.com.caelum.aeris.session;

import javax.ejb.Local;
/*
 * Import referente a anotação @Remove que anota um ou mais métodos responsáveis por representar o fim
 * do ciclo de vida do meu EJB.
 */
import javax.ejb.Remove;
/*
 * Import referente ao container da especificação EJB - Stateful Session Bean.
 * Neste caso estou utilizando um EJB como Managed Bean (O Seam permite essa fácil integração etre JSF e EJB).
 */
import javax.ejb.Stateful;

import org.jboss.seam.annotations.Begin; // Anotação responsável identificar o método que inicia uma conversação long (Log Running Conversation)
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope; 
import org.jboss.seam.log.Log;
/*
 * Quando o Seam "decide" que deve encerrar o EJB, o método anotado com @Destroy será chamado 
 */
import org.jboss.seam.annotations.Destroy;

import br.com.caelum.aeris.entity.Trecho;


@Stateful

@Local(VooHandler.class)
@Name("vooHandler")
@Scope(ScopeType.CONVERSATION)
public class VooHandlerBean implements VooHandler {

	@Logger
	private Log log;
	
	private Trecho trechoSelecionado;
	
	@Begin
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
		log.info("Chamando método de destruição obrigatória");
	}
}
