package com.tiagosilva.crudDeCliente.dto;

import com.tiagosilva.crudDeCliente.domain.CidadeDomain;

public class CidadeDTO {

	private Integer id;
	private String nome;
	
	public CidadeDTO() {
		
	}

	public CidadeDTO(CidadeDomain obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
