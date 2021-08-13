package com.tiagosilva.crudDeCliente.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EnderecoDomain implements Serializable{
	private static final long serialVersionUID = 1L;

	/* ATRIBUTOS */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String cep;
	private String uf;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private ClienteDomain cliente;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private CidadeDomain cidade;
	
	/* CONSTRUTOR PADRAO */
	public EnderecoDomain() {
	}

	/* CONSTRUTOR COM ARGUMENTOS */
	public EnderecoDomain(Integer id, String logradouro, String complemento, String bairro, String cep, String uf,
			ClienteDomain cliente, CidadeDomain cidade) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.uf = uf;
		this.cliente = cliente;
		this.cidade = cidade;
	}

	/* GETTERS E SETTERS */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public ClienteDomain getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDomain cliente) {
		this.cliente = cliente;
	}

	public CidadeDomain getCidade() {
		return cidade;
	}

	public void setCidade(CidadeDomain cidade) {
		this.cidade = cidade;
	}

	/* HASH CODE AND EQUALS*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoDomain other = (EnderecoDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
