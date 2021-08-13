package com.tiagosilva.crudDeCliente.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiagosilva.crudDeCliente.domain.CidadeDomain;
import com.tiagosilva.crudDeCliente.domain.ClienteDomain;
import com.tiagosilva.crudDeCliente.domain.EnderecoDomain;
import com.tiagosilva.crudDeCliente.domain.EstadoDomain;
import com.tiagosilva.crudDeCliente.domain.enums.Perfil;
import com.tiagosilva.crudDeCliente.repositories.CidadeRepository;
import com.tiagosilva.crudDeCliente.repositories.ClienteRepository;
import com.tiagosilva.crudDeCliente.repositories.EnderecoRepository;
import com.tiagosilva.crudDeCliente.repositories.EstadoRepository;

@Service
public class DataBaseService{

	
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {
		

		EstadoDomain est1 = new EstadoDomain(null, "Minas Gerais");
		EstadoDomain est2 = new EstadoDomain(null, "São Paulo");

		CidadeDomain c1 = new CidadeDomain(null, "Uberlandia", est1);
		CidadeDomain c2 = new CidadeDomain(null, "São Paulo", est2);
		CidadeDomain c3 = new CidadeDomain(null, "Campinas", est2);

		est1.getCidade().addAll(Arrays.asList(c1));
		est2.getCidade().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		ClienteDomain cli1 = new ClienteDomain(null, "usuario comum" , "5464565645","user@gmail.com", pe.encode("123456"));
		cli1.getTelefones().addAll(Arrays.asList("345956", "336945425"));

		ClienteDomain cli2 = new ClienteDomain(null, "admin", "91706727097","adming@gmail.com", pe.encode("123456"));
		cli1.getTelefones().addAll(Arrays.asList("40028922", "6665424756"));
		cli2.addPerfil(Perfil.ADMIN);

		EnderecoDomain e1 = new EnderecoDomain(null, "testando", "testando complemento", "Testando bairro", "testando cep", "testando uf", cli1, c1);
		

		cli1.getEnderecos().addAll(Arrays.asList(e1));
		cli1.getEnderecos().addAll(Arrays.asList(e1));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1));
		
	}
}
