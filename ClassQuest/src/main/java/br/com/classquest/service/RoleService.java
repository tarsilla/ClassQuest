package br.com.classquest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.classquest.model.Role;
import br.com.classquest.repository.RoleRepository;

@Service
public class RoleService {
  
	@Autowired
	private RoleRepository repository;
	
	public List<Role> buscarTodos(){
		return repository.findAll();
	}
	
	public Role save(Role role) {
		return repository.saveAndFlush(role);
	}
	
	public Role findByUsername(String nome){
		return repository.findByUsername(nome);
	}
	
}
