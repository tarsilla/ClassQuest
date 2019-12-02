package br.com.classquest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.classquest.model.Email;
import br.com.classquest.model.Usuario;

@Service
public class EmailService { 
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private UsuarioService serviceUsuario;
	
		
	public void sendEmailBemVindo(Email email) {
		try {
			Usuario usuario = serviceUsuario.getEmail(email.getTo());
			email.setFrom("gestaoescolaronline1.0@gmail.com");
			email.getMap().put("name", usuario.getNome());
			email.setSubject("Olá " + usuario.getNome() + ", seja bem-vindo ao site ClassQuest!");
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email.getTo());
			message.setSubject(email.getSubject());
			message.setText("Seja bem vindo ao site ClassQuest,seu site de aprendizado!");
			message.setFrom(email.getFrom());
			
			emailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendNovaSenhaEmail(Email email, String novaSenha) {
		try {
			Usuario usuario = serviceUsuario.getEmail(email.getTo());
			email.setFrom("gestaoescolaronline1.0@gmail.com");
			email.getMap().put("name", usuario.getNome());
			email.setSubject("Olá " + usuario.getNome() + " Confira sua nova senha do ClassQuest!");
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email.getTo());
			message.setSubject(email.getSubject());
			message.setText("Nova senha: " + novaSenha);
			message.setFrom(email.getFrom());
			
			emailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMail(String texto, String email){
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		Usuario usuario = serviceUsuario.getEmail(email);
		
        message.setText(texto);
        message.setSubject("Olá " + usuario.getNome() + " Sua compra foi realizada com Sucesso!");
        message.setTo(email);
        message.setFrom("gestaoescolaronline1.0@gmail.com");
        
        try {
        	emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
