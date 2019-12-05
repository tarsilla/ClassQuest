package br.com.classquest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.classquest.model.Usuario;

@Repository
public interface PerfilRepository extends JpaRepository<Usuario, Long>{

}
