package br.com.classquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/turma")
public class TurmaController {

	@GetMapping("/novaTurma")
	public ModelAndView novaTurma() {
		return new ModelAndView("/turma/novaTurma");	
	}
	
	@GetMapping("/salvar")
	public ModelAndView salvar() {
		return new ModelAndView("/turma/novaTurma");	
	}
	
}
