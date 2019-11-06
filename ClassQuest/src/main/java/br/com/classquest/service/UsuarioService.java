package br.com.classquest.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.classquest.model.Role;
import br.com.classquest.model.Usuario;
import br.com.classquest.repository.UsuarioRepository;


@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Usuario save(Usuario usuario) {
		return repository.saveAndFlush(usuario);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Usuario findByUsername(String username){
		return repository.findByUsername(username);
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = repository.findByUsername(username);
		
		org.springframework.security.core.userdetails.User userFinal = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getPermissoes(user));
		System.out.println(userFinal.getAuthorities());
		return userFinal;
		
	}
	
	
	private Collection<? extends GrantedAuthority> getPermissoes(UserDetails usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			
		Set<Role> permissoes =  ((Usuario) usuario).getRole();
		for( Role r : permissoes ) {
			 authorities.add( new SimpleGrantedAuthority(r.getNome().toUpperCase() ) );
		}
		
		
		return authorities;
	}
}
