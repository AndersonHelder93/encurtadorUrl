package com.encurtador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encurtador.models.Link;
import com.encurtador.models.UsuarioModel;

public interface EncurtadorRepository extends JpaRepository<Link, Long> {

	List<Link> findAll();
	
	List<Link> findByIdUsuario(UsuarioModel usuario);

	Link findById(String linkEncurtado);
}
