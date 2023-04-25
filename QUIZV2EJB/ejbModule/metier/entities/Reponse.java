package metier.entities;

import java.io.Serializable;

import jakarta.persistence.*;
@Entity
public class Reponse implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String enence;
	private String etat;
	@ManyToOne
	private Question question;
	
	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Reponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Reponse(String enence, Question question) {
		super();
		this.enence = enence;
		this.question = question;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnence() {
		return enence;
	}
	public void setEnence(String enence) {
		this.enence = enence;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
}
