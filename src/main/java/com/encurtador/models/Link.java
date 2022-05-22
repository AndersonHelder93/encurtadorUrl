package com.encurtador.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Link {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String urlOriginal;
	private String urlEncurtada;

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

}
