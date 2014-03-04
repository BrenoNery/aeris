package br.com.caelum.aeris.entity;

import java.util.*;

import javax.persistence.*;

import org.hibernate.validator.*; // Anotações de validação do Hibernate Validator

@Entity
public class Trecho {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 20)
	@NotNull
	@Enumerated(EnumType.STRING)
	private Localidade origem;
	
	@Column(length = 20)
	@NotNull
	@Enumerated(EnumType.STRING)
	private Localidade destino;
	
	@OneToMany(mappedBy="trecho", cascade=CascadeType.ALL)
	private List<Voo> voos = new ArrayList<Voo>();
	
	public void addVoo(Voo voo) {
		voo.setTrecho(this);
		this.voos.add(voo);
	}
	
	// getters and setters - Início
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Localidade getOrigem() {
		return origem;
	}

	public void setOrigem(Localidade origem) {
		this.origem = origem;
	}

	public Localidade getDestino() {
		return destino;
	}

	public void setDestino(Localidade destino) {
		this.destino = destino;
	}

	public List<Voo> getVoos() {
		return voos;
	}

	public void setVoos(List<Voo> voos) {
		this.voos = voos;
	}
	// getters and setters - Final
	
	public String toString() {
		return String.format("{Trecho id:%d, origem:%s, destino:%s}", id, origem, destino);
	}
}
