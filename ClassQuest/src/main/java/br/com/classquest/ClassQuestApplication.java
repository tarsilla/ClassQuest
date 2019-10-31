package br.com.classquest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.classquest.controller","br.com.classquest.model","br.com.classquest.enums","br.com.classquest.repository","br.com.classquest.service","br.com.classquest.jobs"})
public class ClassQuestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassQuestApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return agrs -> {
			System.out.println("**** PROJETO CLASSQUEST *****");
			System.out.println("Autor (a): Antonia Tarsilla Costa Lima ");
		};
		
	}

}