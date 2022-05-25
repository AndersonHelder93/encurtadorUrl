package com.encurtador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encurtador.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

	//Realiza a consulta no banco de dados buscando apenas o id
	public UsuarioModel findByLogin(String login);
}
