package com.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import metier.entities.Personne;
import metier.entities.Poseur;
import metier.entities.QuNote;
import metier.entities.Question;
import metier.entities.Quiz;
import metier.entities.Reponse;
import metier.services.IQuizLocale;

@Named("QuizBean")
@SessionScoped
public class QuizBean implements Serializable{
	@EJB
	private IQuizLocale localQuiz;
	private Quiz quiz ;
	private String libelle;
	private List<Question> questions;
	private List<Question> allQuestions;
	private Long questionAddId;
	
	private double quNote;
	
	
	
	public String AddQuestionToQuiz() {
		System.out.println(questionAddId);
		System.out.println(quNote);
		QuNote QuNot = new QuNote();
		Question questionAdded = localQuiz.getQuestionById(this.questionAddId);
		QuNot.setNote(quNote);
		QuNot.setQuestion(questionAdded);
		QuNot.setQuiz(quiz);
		localQuiz.addQuNote(QuNot);
		this.questions = localQuiz.getAllQuestionsByQuizId(this.quiz.getId());
		return "ModifyQuiz";
	}
	public Long getQuestionAddId() {
		return questionAddId;
	}
	public void setQuestionAddId(Long questionAddId) {
		this.questionAddId = questionAddId;
	}
	
	
	
	public List<Question> getAllQuestions() {
		return allQuestions;
	}
	public void setAllQuestions(List<Question> allQuestions) {
		this.allQuestions = allQuestions;
	}
	public double getQuNote() {
		return quNote;
	}
	public void setQuNote(double quNote) {
		this.quNote = quNote;
	}
	private List<Long> selectedAnswers;
	private List<Reponse> UserAnswer;
	private double note;
	
	
	
	
	public String updateQuiz() {
		localQuiz.updateQuiz(this.quiz);
		PerssonneBean.setQuizs(localQuiz.getAllQuiz(PerssonneBean.getId()));
		return "homeP";
	}
	public void deleteQuestionN(Long QuestionId, Long QuizId) {
		localQuiz.deleteQuNote(QuestionId, QuizId);
		ModifyQuiz(QuizId);
	}
	public List<Question> getQuizQuestions(Long QuizId){
		List<Question> qs= localQuiz.getAllQuestionsByQuizId(QuizId);
		return qs;
		
	}
	public String ModifyQuiz(Long quizId) {
		this.quiz = localQuiz.getQuizById(quizId);
		this.questions =this.getQuizQuestions(quizId);
		this.allQuestions = localQuiz.getAllQuestion();
		 return "ModifyQuiz";
	}
	public String creeQuiz() {
		Quiz q = new Quiz();
		q.setLibelle(libelle);
		q.setNote(note);
		Poseur pos = (Poseur)localQuiz.getPersonneById(PerssonneBean.getId());
		q.setPoseur(pos);
		localQuiz.addQuiz(q);
		PerssonneBean.setQuizs(localQuiz.getAllQuiz(PerssonneBean.getId()));
		return "homeP";
	}
	public String deleteQuiz(Long QuizId) {
		localQuiz.deleteQuiz(QuizId);
		PerssonneBean.setQuizs(localQuiz.getAllQuiz(PerssonneBean.getId()));
		return "homeP";
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String passQuiz(Quiz quizOb) {
		this.quiz = quizOb;
		this.note = quizOb.getNote();
		this.questions = localQuiz.getAllQuestionsByQuizId(quizOb.getId());
		this.UserAnswer = new ArrayList<>();
		return "Quiz";
	}
	public List<Long> getSelectedAnswers() {
		return selectedAnswers;
	}

	public void setSelectedAnswers(List<Long> selectedAnswers) {
		this.selectedAnswers = selectedAnswers;
	}
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public Reponse chercheReponse(Long RepId) {
		Reponse rep = localQuiz.getReponseById(RepId);
		this.UserAnswer.add(rep);
		return rep;
	}
	public List<Reponse> getUserAnswer() {
		return UserAnswer;
	}
	public void setUserAnswer(List<Reponse> userAnswer) {
		UserAnswer = userAnswer;
	}
	public int getReponsesCorrect(Long QuestionId) {
		int correctRep = localQuiz.getQestionCorrectReponses(QuestionId);
		return correctRep;
	}
	public String validateReponse(Long QuestionId) {
		double QuestionNote = localQuiz.getQuestionNote(quiz.getId(),QuestionId);
		for(Long l : selectedAnswers) {
			Reponse r = chercheReponse(l);
			if(r.getEtat().equals("True")) note += QuestionNote/getReponsesCorrect(QuestionId) ;
		}
		//System.out.println(getReponsesCorrect(QuestionId));
		questions.remove(0);
		return "Quiz";
	}
	public String addQuiz() {
		
		return "homeP";
	}
}
