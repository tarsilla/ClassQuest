package br.com.classquest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.classquest.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByUsername(String username);
	
	public List<Usuario> findByNomeLike(String name);
	
	@Query
	public Usuario findByEmail(String email);
	@Query
	public Usuario findByEmailAndSenha(String email, String senha);
	@Query
	public Usuario findByNome(String nome);
	
	@Query("select u from Usuario u where u.nome = ?1")
	public List<Usuario> findByName(String nome);
	
	@Query("select u from Usuario u where u.email = ?1")
	public List<Usuario> findEmail(String email);
}
