package br.com.classquest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.classquest.model.Tema;
import br.com.classquest.repository.TemaRepository;

@Service
public class TemaService {

	@Autowired
	private TemaRepository repository;
	
	public List<Tema> findAll(){
		return repository.findAll();
	}
	
	public void cadastrar(Tema tema) {
		repository.saveAndFlush(tema);
	}
	
	public Tema save(Tema tema) {
        return repository.saveAndFlush(tema);
    }
	
	public void update (Long id) {
		// TODO Auto-generated method stub
	}
	
	public void delete (Long id) {
		repository.deleteById(id);
	}
	
	public Tema findOne(Long id) {
		return repository.getOne(id);
	}
}
