package br.com.classquest.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.classquest.enums.Status;
import br.com.classquest.enums.TipoUsuario;
import br.com.classquest.model.Role;
import br.com.classquest.model.Usuario;
import br.com.classquest.service.RoleService;
import br.com.classquest.service.UsuarioService;

@Component
public class Inicializador implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UsuarioService userService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		System.out.println("----- Criando Usuário ------");
		creatUsuarioAdmin();
		System.out.println("----- Usuário Criado com Sucesso! -----");
	}
	
	private void creatUsuarioAdmin() {
		
		String username = "admin";
		Usuario user = userService.findByUsername(username);
		
		/*if(user == null){
			Usuario admin = new Usuario();
			admin.setNome("Professor");
			admin.setEmail("professor@gmail.com");
			admin.setUsername("professor");
			admin.setTipousuario(TipoUsuario.PROFESSOR);
			admin.setStatus(Status.ATIVO);
			admin.setPassword(new BCryptPasswordEncoder().encode("12345"));
			
			Role rolepro = roleService.findByUsername("PRO");
			admin.getRole().add(rolepro);
			
			
			Role rolealu = roleService.findByUsername("ALU");
			admin.getRole().add(rolealu);
			
			userService.save(admin);
					
		}*/
		
	}
	
}
