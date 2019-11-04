package br.com.classquest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Alternativa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String texto;

	@Column(nullable = false)
	private Boolean correta;
	
	@OneToMany(mappedBy = "alternativa")
    private List<QuestaoQuestionario> questaoQuestionario;
	
	@ManyToOne
	private Questao questao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Boolean getCorreta() {
		return correta;
	}

	public void setCorreta(Boolean correta) {
		this.correta = correta;
	}

	public List<QuestaoQuestionario> getQuestaoQuestionario() {
		return questaoQuestionario;
	}

	public void setQuestaoQuestionario(List<QuestaoQuestionario> questaoQuestionario) {
		this.questaoQuestionario = questaoQuestionario;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
