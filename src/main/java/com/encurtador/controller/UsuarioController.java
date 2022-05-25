package com.encurtador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encurtador.config.JWTUtil;
import com.encurtador.dto.TokenDTO;
import com.encurtador.models.UsuarioModel;
import com.encurtador.repository.UsuarioRepository;
import com.encurtador.service.SenhaService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder encoder;
	
	@Autowired
	private SenhaService senhaService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
		this.usuarioRepository = usuarioRepository;
		this.encoder = encoder;
	}
	
	@GetMapping("/listarUsuarios")
	public ResponseEntity<List<UsuarioModel>> listarTodos(){
		
		return ResponseEntity.ok(usuarioRepository.findAll()); 
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenDTO> login(@RequestBody UsuarioModel usuarioModel){
		TokenDTO tokenDto = new TokenDTO();
		boolean valido = senhaService.validarSenha(usuarioModel.getLogin(), usuarioModel.getPassword());
		if(valido == false) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		
		jwtUtil.generateToken(usuarioModel.getLogin());
		tokenDto.setToken(jwtUtil.generateToken(usuarioModel.getLogin()));
		return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuario){
		// intercepta a senha que veio no disparo do endpoint, e a senha foi alterada visualmente com criptografia
		usuario.setPassword(encoder.encode(usuario.getPassword())); 
		return ResponseEntity.ok(usuarioRepository.save(usuario));
	}
	
	
}
