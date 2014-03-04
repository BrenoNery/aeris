package br.com.caelum.aeris.session;

import java.util.*;

import javax.persistence.EntityManager;

import br.com.caelum.aeris.entity.Trecho;
//import br.com.caelum.aeris.exception.DAOException; // Import referente ao tratamento de exceções


import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory; // Import da anotação "@Factory" (Padrão Fábrica)
import org.jboss.seam.annotations.In; // Import da anotação "@In" (Injeção de dependencia)
import org.jboss.seam.annotations.Logger; // Import da anotação "@Logger" (Responsável por implementar a API "commons-logging")
import org.jboss.seam.annotations.Name; // Import da anotação "@Name" (Criando um ManagedBean)
import org.jboss.seam.annotations.Scope; // Import da anotação "@Scope" (Criando um ManagedBean)
import org.jboss.seam.annotations.datamodel.DataModel; // Import da anotação "@DataModel" (Converte um List<> de beans para um DataModel)
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.Events; // Import referente a geração de eventos (Listener / Observer)
import org.jboss.seam.log.Log; // Import responsável por implementar a interface "Log" do JBoss Seam.

@Name("trechoHandler")
@Scope(ScopeType.EVENT)
public class TrechoHandler {
	
	private Trecho trecho = new Trecho();
	
	@In
	private EntityManager entityManager;
	
	@In
	private Events events; 
	
	@DataModel
	private List<Trecho> trechos;
	
	@DataModelSelection
	private Trecho trechoSelecionado;
	
	@Logger
	private Log log;
	
	public void salvar() {
		
		//System.out.println("Salvando: " + trecho);
		log.info("Salvando: #0", this.trecho);
		entityManager.merge(this.trecho);
		
		events.raiseEvent("novoTrecho", this.trecho);
		this.trecho = new Trecho();
	}
	
	public void editar() {
		this.trecho = trechoSelecionado;
	}
	
	public String remover() {
		this.entityManager.remove(trechoSelecionado);
		//throw new DAOException();
		return "/trechos.xhtml";
	}
	
	public Trecho getTrecho() {
		return this.trecho;
	}
	
	@SuppressWarnings("unchecked")
	@Factory("trechos")
	public void populaTrechos() {
		
		//System.out.println("Buscando trechos do Banco de Dados");
		log.info("Buscando trechos do Banco de Dados");
		this.trechos = this.entityManager.createQuery("from Trecho").getResultList();
	}
}
