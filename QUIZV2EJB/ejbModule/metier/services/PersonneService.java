package metier.services;

import java.util.ArrayList;
import java.util.List;


import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import metier.entities.Apprenant;
import metier.entities.Personne;
import metier.entities.Poseur;
import metier.entities.QuNote;
import metier.entities.Question;
import metier.entities.Quiz;
import metier.entities.Reponse;

@Stateless
public class PersonneService implements IQuizLocale, IQuizRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Personne authenticate(String login, String password) {
		Personne perso =new Personne();
		Query query = em.createQuery("select l from Personne l where login=:login and password=:password");
		query.setParameter("login", login);
		query.setParameter("password", password);
		perso = (Personne) query.getSingleResult();
		System.out.println("----------"+perso+"----------");
		return perso;
	}

	@Override
	public void SignUp(String nom, String prenom, String login, String password, char type) {
		Personne user;
		System.out.println(type);
		if(type == 'S') {
			user = new Apprenant(nom, prenom, login, password);
			System.out.println("Student : "+user);
		}else {
			user = new Poseur(nom, prenom, login, password);
			System.out.println("Teacher : "+user);
		}
		em.persist(user);
		
	}
	public List<Quiz> getAllQuiz(Long personne_id){
		Query query;
		if(personne_id != -1) {
			query = em.createQuery("Select q from Quiz q where q.poseur.id=:personne_id",Quiz.class);
			query.setParameter("personne_id", personne_id);
		}else {
			query = em.createQuery("Select q from Quiz q");
		}
		List<Quiz> Quizs = query.getResultList();
		return Quizs;
	}

	@Override
	public List<Question> getAllQuestionsByQuizId(Long quiz_id) {
		Query query = em.createQuery("SELECT q FROM QuNote q WHERE q.quiz.id = :quizId", QuNote.class);
		query.setParameter("quizId", quiz_id);
		List<Question> questions = new ArrayList<>();
		List<QuNote> questionN = query.getResultList();
		for(QuNote q : questionN) {
			questions.add(q.getQuestion());
		}
		return questions;
	}
	@Override
	public List<Question> getAllQuestion(){
		Query q = em.createQuery("SELECT q FROM Question q");
		List<Question> questions = q.getResultList();
		return questions;
	}

	@Override
	public List<Reponse> getAllReponsesByQuestionId(Long questionId) {
		Query query = em.createQuery("SELECT r FROM Reponse r WHERE r.question.id =:questionId");
		query.setParameter("questionId", questionId);
		List<Reponse> reponses = query.getResultList();
		return reponses;
	}
	@Override
	public Reponse getReponseById(Long reponseId) {
		Query query = em.createQuery("SELECT r FROM Reponse r WHERE r.id =:repId");
		query.setParameter("repId", reponseId);
		Reponse r = (Reponse)query.getSingleResult();
		
		return r;
	}
	@Override
	public int getQestionCorrectReponses(Long questionId) {
		Query query = em.createQuery("SELECT r FROM Reponse r WHERE r.etat = 'true' AND r.question.id =:questionId",Reponse.class);
		query.setParameter("questionId", questionId);
		List<Reponse> Correct = query.getResultList();
		int nb = Correct.size();
		return nb;
	}
	@Override
	public double getQuestionNote(Long QuizId, Long QuestionId) {
		Query query = em.createQuery("SELECT q FROM QuNote q WHERE q.quiz.id =:QuizId AND q.question.id =:QuestionId",QuNote.class);
		query.setParameter("QuizId", QuizId);
		query.setParameter("QuestionId", QuestionId);
		QuNote Note = (QuNote)query.getSingleResult();
		return Note.getNote();
	}
	public Quiz getQuizById(Long quizId) {
		Quiz quiz = em.find(Quiz.class,quizId);
		return quiz;
	}
	@Override
	public void addQuiz(Quiz quiz){
		em.persist(quiz);
	}
	
	@Override
	public void deleteQuiz(Long quizId) {
		Quiz quiz = getQuizById(quizId);
		Query query = em.createQuery("DELETE FROM QuNote q WHERE q.quiz.id =:quizId");
		query.setParameter("quizId", quizId);
		int numDeleted = query.executeUpdate();
		em.remove(quiz);
		
		
	}
	public Personne getPersonneById(Long PersonneId) {
		Personne p = em.find(Personne.class, PersonneId);
		return p;
	}
	public void deleteQuNote(Long QuestionId,Long QuizId) {
		Query query = em.createQuery("SELECT q FROM QuNote q WHERE q.quiz.id =:QuizId AND q.question.id =:QuestionId",QuNote.class);
		query.setParameter("QuizId", QuizId);
		query.setParameter("QuestionId", QuestionId);
		QuNote Note = (QuNote)query.getSingleResult();
		em.remove(Note);
	}

	@Override
	public void updateQuiz(Quiz quiz) {
		Quiz oldQuiz = getQuizById(quiz.getId());
		oldQuiz.setLibelle(quiz.getLibelle());
		oldQuiz.setNote(quiz.getNote());
	}
	@Override
	public void addQuNote(QuNote Qunt) {
		em.persist(Qunt);
	}
	@Override
	public Question getQuestionById(Long questionId) {
		Question question = em.find(Question.class, questionId);
		return question;
	}

	@Override
	public void deleteQuestion(Long questionId) {
		Question question = this.getQuestionById(questionId);
		Query query = em.createQuery("DELETE FROM QuNote q WHERE q.question.id =:questionId");
		query.setParameter("questionId", questionId);
		int numDeleted = query.executeUpdate();
		em.remove(question);
		
	}

	@Override
	public void addQuestion(Question question) {
		em.persist(question);
		
	}
	@Override
	public void addReponse(Reponse reponse) {
		em.persist(reponse);
	}
	@Override
	public void updateQuestion(Question question) {
		Question qu = getQuestionById(question.getId());
		qu.setEnence(question.getEnence());
	}
	@Override
	public void deleteReponse(Long ReponseId) {
		Reponse rep = getReponseById(ReponseId);
		em.remove(rep);
	}
}
//from User where username = :username and password = :password