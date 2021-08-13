package com.tiagosilva.crudDeCliente.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiagosilva.crudDeCliente.domain.enums.Perfil;

@Entity
public class ClienteDomain implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/* ATRIBUTOS */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome; 
	private String email;
	private String cpf;
	
	@JsonIgnore
	private String senha;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<EnderecoDomain> enderecos = new ArrayList<>();

	// ESTOU MAPENADO COMO UMA ENTIDADE FRACA, POR ISSO ESTA ANOTACAO
	@ElementCollection
	@CollectionTable(name = "telefone")
	private Set<String> telefones = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfis")
	private Set<Integer> perfis = new HashSet<>();
	
	/* CONSTRUTOR PADRAO JA ADICIONANDO PERFIL DE CLIENTE*/
	public ClienteDomain() {
		addPerfil(Perfil.CLIENTE);
	}

	/* CONSTRUTOR COM ARGUMENTOS */
	public ClienteDomain(Integer id, String nome, String cpf,String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}

	/* GETTERS E SETTERS*/
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	// RETORNA PERFIL DE CLIENTE
	public Set<Perfil> getPerfis(){
		return perfis .stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	// ADICIONAR PERFIL
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public List<EnderecoDomain> getEnderecos() {
		return enderecos;
	}

	public void setEndereco(List<EnderecoDomain> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	/* HASH CODE END EQUALS*/
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
		ClienteDomain other = (ClienteDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
