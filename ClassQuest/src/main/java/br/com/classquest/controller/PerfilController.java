package br.com.classquest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.classquest.model.Usuario;
import br.com.classquest.service.UsuarioService;


@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private UsuarioService userService;

	@GetMapping
	private ModelAndView perfil() {
		
		Usuario usuario = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		ModelAndView mv = new ModelAndView("perfil/perfil");
		mv.addObject("usuario", usuario);

		return mv;
	}
}