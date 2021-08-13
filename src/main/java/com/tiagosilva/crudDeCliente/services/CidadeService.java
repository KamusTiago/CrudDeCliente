package com.tiagosilva.crudDeCliente.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagosilva.crudDeCliente.domain.CidadeDomain;
import com.tiagosilva.crudDeCliente.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public List<CidadeDomain> findByEstado(Integer estadoId){
		return repository.findCidadeDomain(estadoId);
	}
}
