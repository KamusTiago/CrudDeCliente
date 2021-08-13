package com.tiagosilva.crudDeCliente.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.tiagosilva.crudDeCliente.domain.ClienteDomain;
import com.tiagosilva.crudDeCliente.dto.ClienteDTO;
import com.tiagosilva.crudDeCliente.repositories.ClienteRepository;
import com.tiagosilva.crudDeCliente.resourcers.exception.FieldMessage;

public class ClienteUpdateValidador implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt( map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		ClienteDomain aux = repo.findByEmail(objDto.getEmail());
		if (aux != null &&  ! aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}	
