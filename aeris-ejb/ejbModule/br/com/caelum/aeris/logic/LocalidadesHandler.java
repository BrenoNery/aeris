package br.com.caelum.aeris.logic;

//import java.util.*; // Deste import vem os tipos: "Map" e "HashMap" corretos, onde um � compat�vel com o outro.

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory; // Import da anota��o "@Factory" (Utilizando o padr�o Factory)
import org.jboss.seam.annotations.Name; // Import da anota��o "@Name" (Criando um ManagedBean)
// Imports referentes a API de log ("commom-logging")
import org.jboss.seam.log.*;
import org.jboss.seam.annotations.Logger;

import br.com.caelum.aeris.entity.Localidade;

@Name("localidadesHandler")
public class LocalidadesHandler {
	
	@Logger
	private Log log;
	
	@Factory(value="localidades", scope=ScopeType.APPLICATION)
	public Localidade[] initLocalidades() {
		
		//System.out.println("Iniciando localidades");
		log.info("Iniciando lovalidades");
		return Localidade.values();
	}
}
