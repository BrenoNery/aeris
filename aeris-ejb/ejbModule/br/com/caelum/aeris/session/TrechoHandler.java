package br.com.caelum.aeris.session;

import javax.persistence.EntityManager;

import br.com.caelum.aeris.entity.Trecho;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In; // Import da anota��o "@In" (Inje��o de dependencia)
import org.jboss.seam.annotations.Name; // Import da anota��o "@Name" (Criando um ManagedBean)
import org.jboss.seam.annotations.Scope; // Import da anota��o "@Scope" (Criando um ManagedBean)

@Name("trechoHandler")
@Scope(ScopeType.EVENT)
public class TrechoHandler {
	
	private Trecho trecho = new Trecho();
	
	@In
	private EntityManager entityManager;
	
	public void salvar() {
		
		System.out.println("Salvando: " + trecho);
		entityManager.persist(this.trecho);
		this.trecho = new Trecho();
	}
	
	public Trecho getTrecho() {
		return this.trecho;
	}
}
