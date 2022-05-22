package com.encurtador.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.encurtador.models.Link;
import com.encurtador.repository.EncurtadorRepository;

@Service
public class EncurtadorService {

	@Autowired
	private EncurtadorRepository encurtadorRepository;
	
	public EncurtadorService(EncurtadorRepository encurtadorRepository) {
		this.encurtadorRepository = encurtadorRepository;
	}
	
	public List<Link> listar() {
        return encurtadorRepository.findAll();
    }
	
	public static String encurtarUrl(String url) {
		return "http://localhost:8080/encurtadorURL"+ encurtarUrl(url);
	}
	
	/*public String getStringAleatoria() {
		String strAleatoria = "";
		String charsPossiveis = "ABCDEFGHIJKLMNOPQRSTUVYWXZabcdefghijklmnopqrstuvwxyz0123456789";
		Link url = new Link();
		//url.setUrl("https://pbgas.com/");
		String random;
		random =  "";
		for(int i = 0; i < 2; i++) {
			strAleatoria += charsPossiveis.charAt((int) Math.floor(Math.random() * charsPossiveis.length()));
			random = url + strAleatoria;
		}
		
		return random;
	}*/
	
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
	
	
	
	/*public void gerarLink(String link) throws Exception{
		
		if(link == null || link.isEmpty()) {
			throw new Exception("link inválido");
		}
		*/
		
		
		/*String linkEncurtado = geradorString();
		Link linkUrl = encurtadorRepository.findById(linkEncurtado);
		this.gerarLink(link);
		encurtadorRepository.save(linkUrl);
	}*/
	
	
	public Link encurtar(@RequestBody Link cadastroURL) {

        //todo: lógica do link curto aqui, tranformar url em hash e pegar os 6 últimos caracteres e jogar no campo link curto
        cadastroURL.seturlEncurtada(geradorString());
		//cadastroURL.setLinkEncurtado(getStringAleatoria());
        return encurtadorRepository.save(cadastroURL);
        
    }
}
