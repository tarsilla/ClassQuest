package br.com.classquest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.classquest.model.Tema;
import br.com.classquest.service.TemaService;

@Controller
@RequestMapping("/tema")
public class TemaController {

	@Autowired
	private TemaService service;
	
	@PostMapping("/saveTema")
	public ModelAndView saveTurma(@Valid Tema tema,BindingResult result, RedirectAttributes atribute) {
		if(result.hasErrors()) {
			return cadastrar(tema);
		}
		service.save(tema);
		atribute.addFlashAttribute("mensagem","Tema Cadastrado com sucesso!");
		ModelAndView mv = new ModelAndView("redirect:/tema/listaTema");
		return mv;
	}
	
	@RequestMapping("/cadastro") 
	public ModelAndView cadastrar(Tema tema) {
		ModelAndView view = new ModelAndView("tema/novoTema");
		view.addObject("tema", tema);
		return view;
	}
	
	@GetMapping("/editar/{id}")
	private ModelAndView editar( @PathVariable("id") Long id) {
		Tema tema = service.findOne(id);
		return cadastrar(tema);
	}
	
	@GetMapping("/remover/{id}")
	private ModelAndView remover( @PathVariable("id") Long id) {
		service.delete(id);
		return findAll();
	}
	
	@GetMapping("/listaTema")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView("tema/listaTema");
		view.addObject("temas", service.findAll());
		return view;
	}
}
