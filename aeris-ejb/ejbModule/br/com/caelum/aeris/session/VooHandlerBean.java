package br.com.caelum.aeris.session;

import java.util.*;

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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; //Anotação referente ao Contexto de Persistencia Extendido
import javax.persistence.PersistenceContextType;

import org.jboss.seam.annotations.Begin; // Anotação responsável identificar o método que inicia uma conversação long (Log Running Conversation)
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope; 
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Factory;
/*
 * As anotações abaixo permitem a utilização da técnica "Bijection" (Injetar e ejetar o objeto dos ecopos):
 * 1) @In : Injeto a dependencia do objeto "voo"
 * 2) @Out : Limpo a referencia do objeto no escopo do evento
 */
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
/*
 * Quando o Seam "decide" que deve encerrar o EJB, o método anotado com @Destroy será chamado 
 */
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.datamodel.DataModel;

import br.com.caelum.aeris.entity.*;


@Stateful

@Local(VooHandler.class)
@Name("vooHandler")
@Scope(ScopeType.CONVERSATION)
/*
 * O Escopo de conversação é um escopo intermediário:
 * Scope Event - Scope Conversation - Scope Session
 * Um usuário pode estar associado a várias conversações e cada conversação pode durar várias requisições 
 */
public class VooHandlerBean implements VooHandler {

	@Logger
	private Log log;
	
	private Trecho trechoSelecionado;
	
	@DataModel
	private List<Voo> voos;
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@In(required=false)
	@Out(required=false)
	private Voo voo;
	
	public String salvarVoo() {
		
		this.trechoSelecionado.addVoo(this.voo);
		log.info("Salvando voo #0", this.voo);
		this.entityManager.persist(this.voo);
		this.voo = new Voo();
		return "/voos.xhtml";
	}
	
	@Begin
	public String manipulaVoos(Trecho trecho) {
		this.trechoSelecionado = this.entityManager.merge(trecho);
		log.info("Trecho selecionado: #0", this.trechoSelecionado);
		return "/voos.xhtml";
	}
	
	@Factory("voos")
	public void iniciaVoos() {
		
		this.voos = this.trechoSelecionado.getVoos();
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
