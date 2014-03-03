package br.com.caelum.aeris.session;

import java.util.*;

import javax.persistence.EntityManager;

import br.com.caelum.aeris.entity.Trecho;
//import br.com.caelum.aeris.exception.DAOException; // Import referente ao tratamento de exce��es

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory; // Import da anota��o "@Factory" (Padr�o F�brica)
import org.jboss.seam.annotations.In; // Import da anota��o "@In" (Inje��o de dependencia)
import org.jboss.seam.annotations.Logger; // Import da anota��o "@Logger" (Respons�vel por implementar a API "commons-logging")
import org.jboss.seam.annotations.Name; // Import da anota��o "@Name" (Criando um ManagedBean)
import org.jboss.seam.annotations.Scope; // Import da anota��o "@Scope" (Criando um ManagedBean)
import org.jboss.seam.annotations.datamodel.DataModel; // Import da anota��o "@DataModel" (Converte um List<> de beans para um DataModel)
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.log.Log; // Import respons�vel por implementar a interface "Log" do JBoss Seam.

@Name("trechoHandler")
@Scope(ScopeType.EVENT)
public class TrechoHandler {
	
	private Trecho trecho = new Trecho();
	
	@In
	private EntityManager entityManager;
	
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
