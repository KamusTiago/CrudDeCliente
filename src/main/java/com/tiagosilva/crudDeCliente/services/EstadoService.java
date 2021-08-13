package com.tiagosilva.crudDeCliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagosilva.crudDeCliente.domain.EstadoDomain;
import com.tiagosilva.crudDeCliente.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;
	
	public List<EstadoDomain> findAll(){
		return repository.findAllByOrderByNome();
	}
}
