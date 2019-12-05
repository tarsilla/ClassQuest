package br.com.classquest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.classquest.model.Turma;
import br.com.classquest.repository.TurmaRepository;

@Service
public class PerfilService {

	@Autowired
	private TurmaRepository repository;

	public List<Turma> findAll() {
		return repository.findAll();
	}

	public Turma findById(Long id) {
		return repository.getOne(id);
	}

	public Turma save(Turma categoria) {
		return repository.saveAndFlush(categoria);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
