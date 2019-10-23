package br.com.classquest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.classquest.model.Turma;


@Repository
public interface TurmaRepository  extends JpaRepository<Turma, Long>{

}
