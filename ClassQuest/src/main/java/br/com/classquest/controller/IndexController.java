package br.com.classquest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.classquest.service.SessionService;
import br.com.classquest.service.UsuarioService;
import br.com.classquest.model.Usuario;


@Controller
public class IndexController {
	
	@Autowired
	private UsuarioService serviceUsuario;
	
	@Autowired
	private SessionService<Usuario> serviceSession;

	/*@RequestMapping("/")
	public String index() {
		
		Usuario usuarioByEmail = serviceUsuario.getEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		
		serviceSession.criarSession("usuario", usuarioByEmail);
		
		System.out.println("TELA HOME");
		
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.POST,path= {"/index"})
	public String login() {
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.GET,path= {"/entrar"})
	public String entrar() {
		return "login";
	}*/
	
	@PostMapping("/home") 
	public String home() { 
		
		Usuario usuarioByEmail = serviceUsuario.getEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		
		serviceSession.criarSession("usuario", usuarioByEmail);
		
		System.out.println("TELA HOME");
		return "home";
	} 
	 
	@GetMapping("/")
	public String index() {
		System.out.println("TELA LOGIN");
		serviceSession.clearSession();
		return "login";
	}
	
	@GetMapping("/entrar")
	public String entrar() {
		return "login";
	}
}