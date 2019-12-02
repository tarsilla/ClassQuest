package br.com.classquest.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import br.com.classquest.model.Role;
import br.com.classquest.model.Usuario;
import br.com.classquest.service.RoleService;
import br.com.classquest.service.UsuarioService;

@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private RoleService serviceRole;
	
	@Autowired
	private UsuarioService serviceUsuario;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		System.out.println("----- Criando Usuário ------");
		creatUsuarioProfessor();
		createUsuarioAluno() ;
		System.out.println("----- Usuário Criado com Sucesso! -----");
	}
	
	private void creatUsuarioProfessor() {
		Usuario usuario = new Usuario();
		usuario.setEmail("professor@gmail.com");
		usuario.setSenha("123456");
		usuario.setNome("Professor Sicrano da Silva");
		usuario.setUsername("professor");
		Role role = serviceRole.getNome("PROFESSOR");
		if(role == null) {
			role = new Role();
			role.setNome("PROFESSOR");
			serviceRole.save(role);
			usuario.getRole().add(role);
			serviceUsuario.add(usuario); 
		}
	}
	
	private void createUsuarioAluno() {
		Usuario usuario2 = new Usuario();
		usuario2.setEmail("aluno@gmail.com");
		usuario2.setSenha("123456");
		usuario2.setNome("Aluno Fulano da Silva");
		usuario2.setUsername("aluno");
		Role role = serviceRole.getNome("ALUNO");
		if(role == null) {
			role = new Role();
			role.setNome("ALUNO");
			serviceRole.save(role);
			usuario2.getRole().add(role);
			serviceUsuario.add(usuario2); 
		}
	}
}
