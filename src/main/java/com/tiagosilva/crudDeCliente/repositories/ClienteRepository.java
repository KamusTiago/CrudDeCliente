package com.tiagosilva.crudDeCliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tiagosilva.crudDeCliente.domain.ClienteDomain;

public interface ClienteRepository extends JpaRepository<ClienteDomain, Integer> {

	@Transactional(readOnly = true)
	ClienteDomain findByEmail(String email);

}
