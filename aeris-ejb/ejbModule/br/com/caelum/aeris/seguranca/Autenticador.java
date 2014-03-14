package br.com.caelum.aeris.seguranca;

import java.util.*;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

@Name("autenticador")

@Scope(ScopeType.APPLICATION)
public class Autenticador {
	
	@Logger
	private Log log;
	
	@In
	private Identity identity;
	
	@In
	private Credentials credentials;

	private final Map<String, String> roles;
	
	public Autenticador() {
		this.roles = new HashMap<String, String>();
		this.roles.put("aeris", "empresa");
		this.roles.put("cliente", "comprador");
	}
	
	public boolean autenticar() {
		String username = credentials.getUsername();
			
		log.info("Autenticando #0", credentials.getUsername());
		
		if("aeris".equals(username) || "cliente".equals(username)) {
			identity.addRole(this.roles.get(username));
		}
		return true;
	}
}
