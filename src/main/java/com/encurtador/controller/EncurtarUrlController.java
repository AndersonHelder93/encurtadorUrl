package com.encurtador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.encurtador.dto.LinkDTO;
import com.encurtador.models.Link;
import com.encurtador.service.EncurtadorService;

@RestController
public class EncurtarUrlController {
	
	@Autowired
	private EncurtadorService encurtadorService;

	@RequestMapping(value = "/encurtar", method = RequestMethod.POST)
	public Link encurtar(@RequestBody LinkDTO link, @RequestHeader("Authorization") String authToken  ) {
		return encurtadorService.encurtar(link, authToken);
	}
}
