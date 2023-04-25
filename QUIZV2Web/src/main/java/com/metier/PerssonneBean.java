package com.metier;

import java.io.Serializable;
import java.util.List;


import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.*;
import metier.entities.*;
import metier.services.*;

@Named("PerssonneBean")
@SessionScoped
public class PerssonneBean implements Serializable {
	@EJB
	private IQuizLocale localQuiz;
	private static Long id;
	private String nom;
	private String prenom;
	private String login;
	private String password;
	private char type;
	private static List<Quiz> quizs;
	private List<Question> questions;
	private List<Reponse> reponses;
	private Question qCree;
	private String enence;
	private Reponse reponse;
	private String etat;
	
	
	
	
	

	public PerssonneBean() {
		
	}
	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Reponse getReponse() {
		return reponse;
	}

	public void setReponse(Reponse reponse) {
		this.reponse = reponse;
	}

	public Question getqCree() {
		return qCree;
	}
	public void setqCree(Question qCree) {
		this.qCree = qCree;
	}
	public String getEnence() {
		return enence;
	}
	public void setEnence(String enence) {
		this.enence = enence;
	}
	public static Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public String authenticate() {
		Personne perso = localQuiz.authenticate(login, password);
		if(perso!=null) {
			this.id = perso.getId();
			this.nom = perso.getNom();
			this.prenom = perso.getPrenom();
			if(perso.getClass().getSimpleName().equalsIgnoreCase("Apprenant")) {
				this.quizs = localQuiz.getAllQuiz((long)-1);
				return "homeA";
			}
			else {
				quizs = localQuiz.getAllQuiz(id);
				return "homeP";
			}
		}
		return "index.xhtml?notFound=true";
	}
	public String logout() {
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "index?faces-redirect=true";
	  }
	public String backHome() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "homeA";
	}
	public List<Quiz> getQuizs() {
		return quizs;
	}
	public static void setQuizs(List<Quiz> quiz) {
		quizs = quiz;
	}
	public String SignUp() {
		try {
			localQuiz.SignUp(nom, prenom, login, password, type);
		} catch (Exception e) {
			System.out.println("Erreur--------->"+e.getMessage());
			return "index.xhtml?erreur=true";
		}
		return "index";
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAllQuestions() {
		this.questions = localQuiz.getAllQuestion();
		return "Questions";
	}
	public String deleteQuestion(Long QuestionId) {
		localQuiz.deleteQuestion(QuestionId);
		this.questions = localQuiz.getAllQuestion();
		return "Questions";
	}
	public String creeQuestion() {
		this.qCree = new Question();
		this.qCree.setEnence(enence);
		localQuiz.addQuestion(this.qCree);
		this.questions = localQuiz.getAllQuestion();
		return "Questions";
	}
	public String modifyQuestion(Long questionId) {
		this.qCree = localQuiz.getQuestionById(questionId);
		System.out.println(this.qCree.getEnence());
		this.reponses = localQuiz.getAllReponsesByQuestionId(questionId);
		return "ModifyQuestion";
	}
	public String addReponseToQuestion() {
		Reponse rep = new Reponse();
		rep.setEnence(enence);
		rep.setEtat(etat);
		rep.setQuestion(qCree);
		localQuiz.addReponse(rep);
		this.reponses = localQuiz.getAllReponsesByQuestionId(this.qCree.getId());
		return "ModifyQuestion";
	}
	public String deleteReponse(Long RepId) {
		localQuiz.deleteReponse(RepId);
		this.reponses = localQuiz.getAllReponsesByQuestionId(this.qCree.getId());
		return "ModifyQuestion";
	}
	public String updateQuestion() {
		localQuiz.updateQuestion(qCree);
		this.questions = localQuiz.getAllQuestion();
		return "Questions";
	}
	public List<Reponse> getReponses() {
		return reponses;
	}

	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}
}
