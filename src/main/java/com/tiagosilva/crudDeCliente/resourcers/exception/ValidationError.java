package com.tiagosilva.crudDeCliente.resourcers.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	
	private List<FieldMessage> erros = new ArrayList<FieldMessage>();
	
	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addError(String fieldName, String mensagem) {
		erros.add(new FieldMessage(fieldName, mensagem));
		
	}

	public ValidationError(Long timeStamp, Integer status, String error, String message, String path) {
		super(timeStamp, status, error, message, path);
	}	
}
