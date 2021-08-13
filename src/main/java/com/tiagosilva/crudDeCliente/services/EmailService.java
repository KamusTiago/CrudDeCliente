package com.tiagosilva.crudDeCliente.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.tiagosilva.crudDeCliente.domain.ClienteDomain;

@Service
public interface EmailService {

	void sendEmail(SimpleMailMessage msg);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(ClienteDomain cliente, String newPass);

}