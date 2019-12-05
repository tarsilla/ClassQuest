package br.com.classquest.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import br.com.classquest.model.Usuario;
import br.com.classquest.service.UsuarioService;


@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private UsuarioService userService;
	

	@GetMapping("/irperfil")
	private ModelAndView perfil() {
		Usuario user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		ModelAndView mv = new ModelAndView("perfil/perfil");
		mv.addObject("usuario", user);
		return mv;
	}
	
	@RequestMapping(value = "/image/{image_id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("image_id") Long imageId) throws IOException {

		Usuario usuario = userService.findOne(imageId);

		byte[] imageContent = usuario.getImagem();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ModelAndView save() {
		ModelAndView mv = new ModelAndView("perfil/perfil");
		return mv;
	}
}