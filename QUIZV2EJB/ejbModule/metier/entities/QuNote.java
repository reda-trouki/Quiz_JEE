package metier.entities;

import java.io.Serializable;

import jakarta.persistence.*;
@Entity
public class QuNote implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double note;
	@ManyToOne
	private Quiz quiz;
	@ManyToOne
	private Question question;
	
	public QuNote() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuNote(double note, Quiz quiz, Question question) {
		super();
		this.note = note;
		this.quiz = quiz;
		this.question = question;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
}
