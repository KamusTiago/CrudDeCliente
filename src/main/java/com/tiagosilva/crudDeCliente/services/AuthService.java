package com.tiagosilva.crudDeCliente.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiagosilva.crudDeCliente.domain.ClienteDomain;
import com.tiagosilva.crudDeCliente.repositories.ClienteRepository;
import com.tiagosilva.crudDeCliente.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	 public void sendNewPassword(String email){
		 ClienteDomain cliente = clienteRepository.findByEmail(email);
		 if(cliente == null) {
			 throw new ObjectNotFoundException("Email nao encontrado");
		
		 }
		 String newPass = newPassword();
		 cliente.setSenha(pe.encode(newPass));
		 clienteRepository.save(cliente);
		 emailService.sendNewPasswordEmail(cliente, newPass);
	 }

	private String newPassword() {
		char[] vet = new char[10];
		for (int i= 0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	// FUNCAO PARA GERAR SENHA ALEATORIA
	// USEI TABELA UNICODE
	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) {// GERA UM DIGITO
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt==1) {// GERA LETRA MAIUSCULA
			return (char) (rand.nextInt(26)+ 65);
		}
		else {// GERA LETRA MINUSCULA
			return (char) (rand.nextInt(26)+ 97);
		}
	}
	
}