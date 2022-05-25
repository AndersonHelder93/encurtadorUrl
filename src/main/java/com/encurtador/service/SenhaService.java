package com.encurtador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.encurtador.models.UsuarioModel;
import com.encurtador.repository.UsuarioRepository;

@Service
public class SenhaService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder encoder;

public Boolean validarSenha(@RequestParam String login, @RequestParam String password){
		
		//Consulta no banco de dados se o usuario está cadastrado e faz a validação
		UsuarioModel optUsuario = usuarioRepository.findByLogin(login);
		if (optUsuario == null) {
			return false;
		}
		
		//Consulta no banco de dados se a senha está vinculada com o usuaário cadastrado e faz a validação
		boolean valid = encoder.matches(password, optUsuario.getPassword());

		return valid;
		
	}
}
