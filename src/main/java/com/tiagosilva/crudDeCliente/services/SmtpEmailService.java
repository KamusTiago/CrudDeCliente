package com.tiagosilva.crudDeCliente.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService{

	@Autowired
	private MailSender mailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("SIMULANDO ENVIO DE EMAIL");
		mailSender.send(msg);
		LOG.info("EMAIL ENVIADO!");
		
	}


	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("SIMULANDO ENVIO DE EMAIL HTML");
		javaMailSender.send(msg);
		LOG.info("EMAIL ENVIADO!");
		
	}

}
