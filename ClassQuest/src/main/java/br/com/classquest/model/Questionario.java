package br.com.classquest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Questionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 100, nullable = false)
	@NotBlank(message="Titulo é uma informação obrigatoria!")
	private String titulo;
	
	@Column(columnDefinition = "TEXT", nullable = true)
	@NotBlank(message="Descrição é uma informação obrigatoria!")
	private String descricao;
	
	@Column(nullable = false)
	@NotBlank(message="Tempo é uma informação obrigatoria!")
	private int tempo;
	
	@Column(nullable = false)
	private int numQuestoes;
	
	@ManyToOne
	private Turma turma;
	
	@OneToMany(mappedBy = "questionario")
	private List<QuestaoQuestionario> questoes;
	
	public Questionario() {
		tempo = 1;
		numQuestoes = 10;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public int getNumQuestoes() {
		return numQuestoes;
	}

	public void setNumQuestoes(int numQuestoes) {
		this.numQuestoes = numQuestoes;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<QuestaoQuestionario> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<QuestaoQuestionario> questoes) {
		this.questoes = questoes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
