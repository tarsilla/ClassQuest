package br.com.classquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST,path= {"/home"})
	public String home() {
		return "home";
	}
	
	@RequestMapping(method=RequestMethod.GET,path= {"/entrar"})
	public String entrar() {
		return "login";
	}
}