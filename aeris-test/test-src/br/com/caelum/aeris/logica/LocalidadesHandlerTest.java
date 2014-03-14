package br.com.caelum.aeris.logica;

import java.util.*;

import junit.framework.TestCase;
import br.com.caelum.aeris.entity.Localidade;
import br.com.caelum.aeris.logic.LocalidadesHandler;

public class LocalidadesHandlerTest extends TestCase {

	public void testDeveFornecerTodasAsLocalidades() {
		List<Localidade> todas = Arrays.asList(Localidade.values());
			
		LocalidadesHandler handler = new LocalidadesHandler();
		List<Localidade> localidades = Arrays.asList(handler.initLocalidades());
		
		assertEquals(todas, localidades);
	}
	
}
