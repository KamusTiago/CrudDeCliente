package com.tiagosilva.crudDeCliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagosilva.crudDeCliente.domain.EnderecoDomain;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoDomain, Integer> {

}
