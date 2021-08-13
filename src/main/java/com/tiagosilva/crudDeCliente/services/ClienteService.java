package com.tiagosilva.crudDeCliente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiagosilva.crudDeCliente.domain.CidadeDomain;
import com.tiagosilva.crudDeCliente.domain.ClienteDomain;
import com.tiagosilva.crudDeCliente.domain.EnderecoDomain;
import com.tiagosilva.crudDeCliente.domain.enums.Perfil;
import com.tiagosilva.crudDeCliente.dto.ClienteDTO;
import com.tiagosilva.crudDeCliente.dto.ClienteNewDTO;
import com.tiagosilva.crudDeCliente.repositories.ClienteRepository;
import com.tiagosilva.crudDeCliente.repositories.EnderecoRepository;
import com.tiagosilva.crudDeCliente.security.UserSS;
import com.tiagosilva.crudDeCliente.services.exceptions.AuthorizationException;
import com.tiagosilva.crudDeCliente.services.exceptions.DataIntegrityException;
import com.tiagosilva.crudDeCliente.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder pe;
	
	public ClienteDomain find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}

		Optional<ClienteDomain> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ClienteDomain.class.getName()));
	}

	@Transactional
	public ClienteDomain insert(ClienteDomain obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;

	}

	public ClienteDomain update(ClienteDomain obj) {
		ClienteDomain newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente porque existe alguma integridade de dados");
		}
	}

	public List<ClienteDomain> findAll() {
		return repo.findAll();
	}
	
	public ClienteDomain findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		ClienteDomain obj = repo.findByEmail(email);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id:" + user.getId() + ", Tipo: " + 	ClienteDomain.class.getName());
		}
		return obj;
	}

	public Page<ClienteDomain> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public ClienteDomain fromDTO(ClienteDTO objDto) {
		return new ClienteDomain(objDto.getId(),objDto.getNome(), objDto.getEmail(), null,null);
	}

	public ClienteDomain fromDTO(ClienteNewDTO objDto) {
		ClienteDomain cli = new ClienteDomain(null, objDto.getNome(), objDto.getEmail(), objDto.getCpf(),
				 pe.encode(objDto.getSenha()));
		CidadeDomain cid = new CidadeDomain(objDto.getCidadeId(), null, null);
		EnderecoDomain end = new EnderecoDomain(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;

	}

	private void updateData(ClienteDomain newObj, ClienteDomain obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
