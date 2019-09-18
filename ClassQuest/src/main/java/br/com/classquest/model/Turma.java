package br.com.classquest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import br.com.classquest.enums.Status;

@Entity
public class Turma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 50, nullable = false)
	@NotBlank(message="Nome é uma informação obrigatoria!")
	private String nome;
	
	@Column(length = 10, nullable = false)
	@NotBlank(message="Código é uma informação obrigatoria!")
	private String codigo;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	private Professor professor;
	
	@ManyToMany(mappedBy = "turmas")
	private List<Aluno> alunos;
	
	@OneToMany(mappedBy = "turma")
	private List<Questionario> questionarios;
	
	public Turma() {
		status = Status.ATIVO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Questionario> getQuestionarios() {
		return questionarios;
	}

	public void setQuestionarios(List<Questionario> questionarios) {
		this.questionarios = questionarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
