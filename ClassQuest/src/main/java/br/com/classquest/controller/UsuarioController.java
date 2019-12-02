package br.com.classquest.controller;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.classquest.model.Email;
import br.com.classquest.model.Usuario;
import br.com.classquest.service.EmailService;
import br.com.classquest.service.RoleService;
import br.com.classquest.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private RoleService servicerole;
	
	@Autowired
	private EmailService sendEmail;
	
	@GetMapping("/add")
	public ModelAndView add(Usuario usuario) {
		
		ModelAndView mv = new ModelAndView("usuario/cadastro");
		mv.addObject("usuario", usuario);
		mv.addObject("roles", servicerole.buscarTodos());
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Usuario usuario, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(usuario);
		}
		
		Usuario usuario2 = service.getEmail(usuario.getEmail());
		ModelAndView view = new ModelAndView("login");
		
		if(usuario2 != null) {	
			view.addObject("error", "Email já está cadastrado no sistema!");
		}else {
			service.add(usuario);
			Email email = new Email();
			email.setTo(usuario.getEmail());
			sendEmail.sendEmailBemVindo(email);
			view.addObject("mensagem", "Olá seu cadastrado foi realizado com sucesso em nosso site.");	
		}
		return view;
	}
	
	@GetMapping("/atualizar") 
	public ModelAndView atualizar() {
		ModelAndView mv = new ModelAndView("usuario/esqueceuSenha");
		return mv;
	}  
	
	@PostMapping("/update")
	public ModelAndView update(@RequestParam("email") String email) {
		
		Usuario usuario2 = service.getEmail(email);
		ModelAndView view = new ModelAndView("login");
		if(usuario2 == null) {
			
				view.addObject("error", "Email não está cadastrado no sistema!");
		}else {
			Random r = new Random();
			String novaSenhaGerada = Integer.toString(Math.abs(r.nextInt()), 36).substring(0, 6);
			System.out.println(novaSenhaGerada);
			usuario2.setSenha(novaSenhaGerada);
			service.add(usuario2);
			Email email2 = new Email();
			email2.setTo(usuario2.getEmail());
			sendEmail.sendNovaSenhaEmail(email2, novaSenhaGerada);
			view.addObject("mensagem", "Nova senha gerada!!!");
		}
		return view;		
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("usuario/lista");
		mv.addObject("usuarios", service.findAll());
		
		return mv;
	}
	
	
}