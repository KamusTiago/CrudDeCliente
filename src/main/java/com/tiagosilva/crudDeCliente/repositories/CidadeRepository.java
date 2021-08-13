package com.tiagosilva.crudDeCliente.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tiagosilva.crudDeCliente.domain.CidadeDomain;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeDomain, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM CidadeDomain obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
	public List<CidadeDomain> findCidadeDomain(@Param("estadoId")Integer estado_id);
}
