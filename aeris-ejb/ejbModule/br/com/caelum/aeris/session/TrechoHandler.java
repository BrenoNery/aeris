package br.com.caelum.aeris.session;

import java.util.*;

import javax.persistence.EntityManager;

import br.com.caelum.aeris.entity.Trecho;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory; // Import da anotação "@Factory" (Padrão Fábrica)
import org.jboss.seam.annotations.In; // Import da anotação "@In" (Injeção de dependencia)
import org.jboss.seam.annotations.Name; // Import da anotação "@Name" (Criando um ManagedBean)
import org.jboss.seam.annotations.Scope; // Import da anotação "@Scope" (Criando um ManagedBean)
import org.jboss.seam.annotations.datamodel.DataModel; // Import da anotação "@DataModel" (Converte um List<> de beans para um DataModel)

@Name("trechoHandler")
@Scope(ScopeType.EVENT)
public class TrechoHandler {
	
	private Trecho trecho = new Trecho();
	
	@In
	private EntityManager entityManager;
	
	@DataModel
	private List<Trecho> trechos;
	
	public void salvar() {
		
		System.out.println("Salvando: " + trecho);
		entityManager.persist(this.trecho);
		this.trecho = new Trecho();
	}
	
	public Trecho getTrecho() {
		return this.trecho;
	}
	
	@SuppressWarnings("unchecked")
	@Factory("trechos")
	public void populaTrechos() {
		System.out.println("Buscando trechos do Banco de Dados");
		this.trechos = this.entityManager.createQuery("from Trecho").getResultList();
	}
}
