package br.com.caelum.aeris.logic;

import java.util.*; // Deste import vem os tipos: "Map" e "HashMap" corretos, onde um é compatível com o outro.

//import org.jboss.seam.annotations.Factory; // Import da anotação "@Factory" (Utilizando o padrão Factory)
import org.jboss.seam.annotations.Name; // Import da anotação "@Name" (Criando um ManagedBean)

import br.com.caelum.aeris.entity.Localidade;

@Name("localidadesHandler")
public class LocalidadesHandler {
	
	//@Factory("localidades")
	public Map<String, Localidade> getLocalidades() {
		System.out.println("Iniciando localidades");
		
		Map<String, Localidade> opcoes = new HashMap<String, Localidade>();
		
		for(Localidade l: Localidade.values()) {
			opcoes.put(l.getNome(), l);
		}
		
		return opcoes;
	}
}
