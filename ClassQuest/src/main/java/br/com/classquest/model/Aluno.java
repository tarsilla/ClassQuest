package br.com.classquest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Aluno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	@NotBlank(message="Pontuação é uma informação obrigatoria!")
	private int pontuacao;
	
	@ManyToMany
	private List<Turma> turmas;
	
	@ManyToMany
	private List<QuestaoQuestionario> respostas;
	
	public Aluno() {
		pontuacao = 0;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<QuestaoQuestionario> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<QuestaoQuestionario> respostas) {
		this.respostas = respostas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
