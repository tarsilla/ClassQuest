package br.com.classquest.repository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.classquest.service.UsuarioService;


@Repository
@Transactional
public class UserDetailsRepository implements UserDetailsService {

	@Autowired
	private UsuarioService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		br.com.classquest.model.Usuario usuario = userService.findByUsername(username);

		if (usuario == null)
			throw new UsernameNotFoundException("Usuario não encontrado!");

		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
	}

}
