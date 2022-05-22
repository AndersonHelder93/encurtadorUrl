package com.encurtador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encurtador.models.Link;
import com.encurtador.service.EncurtadorService;

@RestController
public class ListarUrlsEncurtadas {
	
	@Autowired
	private EncurtadorService encurtadorService;

	@RequestMapping("/listar")
	public ResponseEntity<List<Link>> listar(){
		return new ResponseEntity<List<Link>>(encurtadorService.listar(), HttpStatus.OK);
	}
}
