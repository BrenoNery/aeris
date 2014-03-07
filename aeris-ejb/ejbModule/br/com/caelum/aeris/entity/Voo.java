package br.com.caelum.aeris.entity;

import java.util.Date;

import javax.persistence.*;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.hibernate.validator.*;

import br.com.caelum.aeris.service.Maiusculo;

@Entity

@Name("voo")
@Scope(ScopeType.EVENT)
public class Voo {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@NotEmpty
	@Length(min = 6, max = 6)
	//@Pattern(regex = "[A-Z]{2}-[0-9]{3}") // A express�o regular n�o est� validando corretamente
	@Maiusculo
	private String codigo;
		
	@Temporal(TemporalType.DATE)
	private Date dataPartida = new Date();
	
	@Temporal(TemporalType.TIME)
	private Date horaPartida = new Date();
	
	@Temporal(TemporalType.DATE)
	@Future // Imp�e uma restri��o de que a data deve ser futura
	private Date dataChegada = new Date();
	
	@Temporal(TemporalType.TIME)
	private Date horaChegada = new Date();
	
	@NotNull
	@ManyToOne
	private Trecho trecho;
	
	// getters and setters - In�cio
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}

	public Date getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(Date horaPartida) {
		this.horaPartida = horaPartida;
	}

	public Date getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Date getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(Date horaChegada) {
		this.horaChegada = horaChegada;
	}

	public Trecho getTrecho() {
		return trecho;
	}

	public void setTrecho(Trecho trecho) {
		this.trecho = trecho;
	}
	// getters and setters - Final
	
	public String toString() {
		return String.format(
				"{Voo id=%d, codigo=%s, horaPartida=%tR, dataPartida=%tD, trecho=%s}",
				id, codigo, horaPartida, dataPartida, trecho);
	}
}
