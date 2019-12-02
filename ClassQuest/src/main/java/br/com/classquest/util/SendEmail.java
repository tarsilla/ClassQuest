package br.com.classquest.util;

import br.com.classquest.model.Email;

public interface SendEmail {
	public void sendEmailText(Email email, String texto);
}
