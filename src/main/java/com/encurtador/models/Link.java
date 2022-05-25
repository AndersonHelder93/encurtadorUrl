package com.encurtador.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Link {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String urlOriginal;
	private String urlEncurtada;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable=false)
	private UsuarioModel idUsuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String geturlOriginal() {
		return urlOriginal;
	}

	public void seturlOriginal(String urlOriginal) {
		this.urlOriginal = urlOriginal;
	}

	public String geturlEncurtada() {
		return urlEncurtada;
	}

	public void seturlEncurtada(String urlEncurtada) {
		this.urlEncurtada = urlEncurtada;
	}

	public UsuarioModel getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UsuarioModel idUsuario) {
		this.idUsuario = idUsuario;
	}

	
}
