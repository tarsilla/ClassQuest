package br.com.classquest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.classquest.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long>{

}
