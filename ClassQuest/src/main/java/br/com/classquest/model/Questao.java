package br.com.classquest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.com.classquest.enums.Dificuldade;

@Entity
public class Questao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String informacao;
	
	@Transient
    private List<Long> i;
	
	private Dificuldade dificuldade;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questao", orphanRemoval = true)
	private List<Alternativa> alternativas;
	
	@OneToMany(mappedBy = "questao")
	private List<QuestaoQuestionario> questionarios;
	
	@ManyToOne
	private Tema tema;
		
	@ManyToOne
	private Professor professor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInformacao() {
		return informacao;
	}

	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}

	public List<Long> getI() {
		return i;
	}

	public void setI(List<Long> i) {
		this.i = i;
	}

	public Dificuldade getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public List<QuestaoQuestionario> getQuestionarios() {
		return questionarios;
	}

	public void setQuestionarios(List<QuestaoQuestionario> questionarios) {
		this.questionarios = questionarios;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
