package br.com.caelum.aeris.session;

import java.util.*;

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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; //Anota��o referente ao Contexto de Persistencia Extendido
import javax.persistence.PersistenceContextType;

import org.jboss.seam.annotations.Begin; // Anota��o respons�vel identificar o m�todo que inicia uma conversa��o long (Log Running Conversation)
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope; 
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Factory;
/*
 * As anota��es abaixo permitem a utiliza��o da t�cnica "Bijection" (Injetar e ejetar o objeto dos ecopos):
 * 1) @In : Injeto a dependencia do objeto "voo"
 * 2) @Out : Limpo a referencia do objeto no escopo do evento
 */
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
/*
 * Quando o Seam "decide" que deve encerrar o EJB, o m�todo anotado com @Destroy ser� chamado 
 */
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.datamodel.DataModel;

import br.com.caelum.aeris.entity.*;


@Stateful

@Local(VooHandler.class)
@Name("vooHandler")
@Scope(ScopeType.CONVERSATION)
/*
 * O Escopo de conversa��o � um escopo intermedi�rio:
 * Scope Event - Scope Conversation - Scope Session
 * Um usu�rio pode estar associado a v�rias conversa��es e cada conversa��o pode durar v�rias requisi��es 
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
		log.info("Chamando m�todo de destrui��o obrigat�ria");
	}
}
