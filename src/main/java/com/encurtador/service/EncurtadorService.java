package com.encurtador.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.encurtador.config.JWTUtil;
import com.encurtador.dto.LinkDTO;
import com.encurtador.models.Link;
import com.encurtador.models.UsuarioModel;
import com.encurtador.repository.EncurtadorRepository;
import com.encurtador.repository.UsuarioRepository;

@Service
public class EncurtadorService {

	@Autowired
	private EncurtadorRepository encurtadorRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	public EncurtadorService(EncurtadorRepository encurtadorRepository) {
		this.encurtadorRepository = encurtadorRepository;
	}
	
	public List<Link> listar() {
        return encurtadorRepository.findAll();
    }
	
	public static String encurtarUrl(String url) {
		return "http://localhost:8080/encurtadorURL"+ encurtarUrl(url);
	}
	public String geradorString() {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZabcdefghijklmnopqrstuvwxyz@!#$&";
		
		Random random = new Random();
		
		String armazenaLetras = "";
		
		String padrao = "https://pbgas.com/";
		
		int index = -1;
		for(int i = 0; i < 3; i++) {
			index = random.nextInt(letras.length());
			armazenaLetras += letras.substring(index, index + 1);
			padrao = padrao + armazenaLetras;
		}
		
		return padrao;
	}
	
			
	public Link encurtar(LinkDTO cadastroURL, String token) {
		if(jwtUtil.isTokenValid(token) == false) {
			return null;
		}
		
		String loginUsuario =  jwtUtil.getSubject(token) ;
		
		 UsuarioModel user = usuarioRepository.findByLogin(loginUsuario);
		
        Link link = new Link();
        link.seturlEncurtada(geradorString());
        cadastroURL.getUrlOriginal();
		link.setIdUsuario(user);
		link.seturlOriginal(cadastroURL.getUrlOriginal());
        return encurtadorRepository.save(link);
        
    }
}
