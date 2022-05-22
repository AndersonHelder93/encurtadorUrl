package com.encurtador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.encurtador.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

	//Realiza a consulta no banco de dados buscando apenas o id
	public Optional<UsuarioModel> findByLogin(String login);
}
