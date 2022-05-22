package com.encurtador.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.encurtador.models.UsuarioModel;
import com.encurtador.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder encoder;
	
	public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
		this.usuarioRepository = usuarioRepository;
		this.encoder = encoder;
	}
	
	@GetMapping("/listarUsuarios")
	public ResponseEntity<List<UsuarioModel>> listarTodos(){
		
		return ResponseEntity.ok(usuarioRepository.findAll()); 
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuario){
		// intercepta a senha que veio no disparo do endpoint, e a senha foi alterada visualmente com criptografia
		usuario.setPassword(encoder.encode(usuario.getPassword())); 
		return ResponseEntity.ok(usuarioRepository.save(usuario));
	}
	
	@GetMapping("/validarSenha")
	public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String password){
		
		//Consulta no banco de dados se o usuario está cadastrado e faz a validação
		Optional<UsuarioModel> optUsuario = usuarioRepository.findByLogin(login);
		if (optUsuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		
		//Consulta no banco de dados se a senha está vinculada com o usuaário cadastrado e faz a validação
		UsuarioModel usuario = optUsuario.get();
		boolean valid = encoder.matches(password, usuario.getPassword());
		
		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		
		return ResponseEntity.status(status).body(valid);
		
	}
}
