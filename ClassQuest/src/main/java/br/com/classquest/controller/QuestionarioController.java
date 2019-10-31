package br.com.classquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/questao")
public class QuestionarioController {

	@GetMapping("/questao")
	   public ModelAndView view (){
		   return new ModelAndView("/questionario/questao");
	}
}
