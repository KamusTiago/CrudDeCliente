package com.tiagosilva.crudDeCliente.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tiagosilva.crudDeCliente.domain.EstadoDomain;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoDomain, Integer> {

	@Transactional(readOnly = true)
	public List<EstadoDomain> findAllByOrderByNome();
}
