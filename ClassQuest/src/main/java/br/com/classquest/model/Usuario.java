package br.com.classquest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;


import br.com.classquest.enums.Status;

@Entity
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false, length=70)
	@NotBlank(message="Nome é uma informação obrigatoria!")
	private String nome;
	
	@Column(nullable=false, length=70)
	@NotBlank(message="Email é uma informação obrigatoria!")
	private String email;
	
	@Column(nullable=false, length=70)
	@NotBlank(message="Senha é uma informação obrigatoria!")
	private String senha;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Lob
	private byte[] foto;
	
	@OneToOne(optional = true)
	private Aluno aluno;
	
	@OneToOne(optional = true)
	private Professor professor;
	
	public Usuario() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
