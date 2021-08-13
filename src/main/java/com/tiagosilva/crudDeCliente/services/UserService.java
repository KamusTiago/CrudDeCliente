package com.tiagosilva.crudDeCliente.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tiagosilva.crudDeCliente.security.UserSS;

public class UserService {

	// METODO QUE CHAMA FUNCAO DO SPRINGSECURITY RETORNANDO USUARIO LOGADO
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		} catch (Exception e) {
			return null;
		}

	}
}