package br.com.classquest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.classquest.model.Turma;
import br.com.classquest.repository.TurmaRepository;


@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repository;
	
	public List<Turma> findAll(){
		return repository.findAll();
	}
	
	public void cadastrar(Turma turma) {
		repository.saveAndFlush(turma);
	}
	
	public Turma save(Turma turma) {
        return repository.saveAndFlush(turma);
    }
	
	public void update (Long id) {
		// TODO Auto-generated method stub
	}
	
	public void delete (Long id) {
		repository.deleteById(id);
	}
	
	public Turma findOne(Long id) {
		return repository.getOne(id);
	}
}
