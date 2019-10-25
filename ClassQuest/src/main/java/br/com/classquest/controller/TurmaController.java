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

import br.com.classquest.model.Turma;
import br.com.classquest.service.TurmaService;

@Controller
@RequestMapping("/turma")
public class TurmaController {
	
	@Autowired
	private TurmaService service;
	
	@PostMapping("/saveTurma")
	public ModelAndView saveTurma(@Valid Turma turma,BindingResult result, RedirectAttributes atribute) {
		if(result.hasErrors()) {
			return cadastrar(turma);
		}
		service.save(turma);
		atribute.addFlashAttribute("mensagem","Turma Cadastrada com sucesso!");
		ModelAndView mv = new ModelAndView("redirect:/turma/minhaTurma");
		return mv;
	}
	
	@RequestMapping("/cadastro")
	public ModelAndView cadastrar(Turma turma) {
		ModelAndView view = new ModelAndView("turma/novaTurma");
		view.addObject("turma", turma);
		return view;
	}
	
	@GetMapping("/editar/{id}")
	private ModelAndView editar( @PathVariable("id") Long id) {
		Turma turma = service.findOne(id);
		return cadastrar(turma);
	}
	
	@GetMapping("/remover/{id}")
	private ModelAndView remover( @PathVariable("id") Long id) {
		service.delete(id);
		return findAll();
	}
	
	@GetMapping("/minhaTurma")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView("turma/minhaTurma");
		view.addObject("turmas", service.findAll());
		return view;
	}
	
	@GetMapping("/verTurma")
	public ModelAndView verTurma() {
		return new ModelAndView("turma/verTurma");	
	}
	
}
