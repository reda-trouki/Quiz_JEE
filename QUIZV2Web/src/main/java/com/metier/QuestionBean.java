package com.metier;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import metier.entities.Question;
import metier.entities.Reponse;
import metier.services.IQuizLocale;

@Named("QuestionBean")
@SessionScoped
public class QuestionBean implements Serializable{
	
		@EJB
		private IQuizLocale localQuiz;
		private Long id;
		private String enence;
		private Question question;
		private List<Reponse> reponses;
		

		
		public QuestionBean() {
			
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

		public List<Reponse> getReponses() {
			return reponses;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public List<Reponse> getReponses(Long questionId) {
			this.reponses = localQuiz.getAllReponsesByQuestionId(questionId);
			return reponses;
		}

		public void setReponses(List<Reponse> reponses) {
			this.reponses = reponses;
		}
		
}
