package br.com.classquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/questao")
public class QuestaoController {

	@GetMapping("/questao")
	   public ModelAndView view (){
		   return new ModelAndView("/questoes/questao");
	}
	
	@GetMapping("/gerenciar")
	   public ModelAndView gerenciar (){
		   return new ModelAndView("/questoes/gerenciar");
	}
	
	@GetMapping("/consultar")
	   public ModelAndView consultar (){
		   return new ModelAndView("/questoes/consultar");
	}
}
