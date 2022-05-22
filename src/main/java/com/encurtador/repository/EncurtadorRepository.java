package com.encurtador.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.encurtador.models.Link;

public interface EncurtadorRepository extends CrudRepository<Link, Long>{

	List<Link> findAll();

	Link findById(String linkEncurtado);
}
