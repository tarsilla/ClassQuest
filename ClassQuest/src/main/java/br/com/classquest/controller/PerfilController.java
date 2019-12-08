package br.com.classquest.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.classquest.model.Usuario;
import br.com.classquest.service.UsuarioService;


@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private UsuarioService userService;
	

	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	

	/*@GetMapping("/irperfil")
	private ModelAndView perfil(Usuario usuario) {
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     
	      String name = auth.getName(); 
	      System.out.println("@@@=================" + name);
			Usuario user = userService.buscarPorEmail(name);
		
		ModelAndView mv = new ModelAndView("perfil/perfil");
		System.out.println("=================" + user.getNome());
		mv.addObject("usuario", user);
		return mv;
	}*/
	
	@GetMapping("/irperfil")
	private ModelAndView perfil() {
		//Usuario user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Usuario user = userService.getEmail(SecurityContextHolder.getContext().getAuthentication().getName());
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
	public ModelAndView save(Usuario usuario, @RequestParam("uploadfile") MultipartFile file,
			@RequestParam("novaSenha") String novaSenha, @RequestParam("confirmeSenha") String confirmeSenha,
			RedirectAttributes ra) throws IOException {

		Usuario userBanco = userService.findById(usuario.getId());

		System.out.println("\n\n\nNova senha: " + novaSenha + "\n\n\n");

		if (!file.isEmpty()) {
			usuario.setImagem(file.getBytes());
		} else {
			usuario.setImagem(userBanco.getImagem());
		}

		if (usuario.getPassword() != null && !novaSenha.isEmpty() && !confirmeSenha.isEmpty()) {

			if (userBanco.getPassword().equals(passwordEncode.encode(novaSenha))) {
				if (novaSenha.equals(confirmeSenha)) {
					usuario.setSenha(passwordEncode.encode(novaSenha));
				}
			} else {
				ra.addFlashAttribute("mensagemErro", "Você tentou mudar a senha, porém as senhas não coincidem");
				return new ModelAndView("redirect:/perfil");
			}
		}

		usuario.setSenha(userBanco.getPassword());
		usuario.setDataCriacao(userBanco.getDataCriacao());
		userService.add(usuario);
		ra.addFlashAttribute("mensagem", "Perfil editado com sucesso");

		return new ModelAndView("redirect:/perfil/irperfil");
	}

}