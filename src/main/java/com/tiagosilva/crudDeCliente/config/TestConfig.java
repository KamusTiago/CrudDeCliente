package com.tiagosilva.crudDeCliente.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tiagosilva.crudDeCliente.services.DataBaseService;
import com.tiagosilva.crudDeCliente.services.EmailService;
import com.tiagosilva.crudDeCliente.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DataBaseService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
