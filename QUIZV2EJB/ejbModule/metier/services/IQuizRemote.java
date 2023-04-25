package metier.services;
import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.Remote;
import metier.entities.*;
@Remote
public interface IQuizRemote {
	
	public Personne authenticate(String login ,String password);
	public void SignUp(String nom, String prenom, String login, String password,char type);
	//quiz
	public List<Quiz> getAllQuiz(Long personne_id);
	public List<Question> getAllQuestionsByQuizId(Long quizId);
	public List<Reponse> getAllReponsesByQuestionId(Long questionId);
	public Reponse getReponseById(Long reponseId);
	public int getQestionCorrectReponses(Long questionId);
	public double getQuestionNote(Long QuizId, Long QuestionId);
	public List<Question> getAllQuestion();
	public void addQuiz(Quiz quiz);
	public void deleteQuiz(Long quizId);
	public Quiz getQuizById(Long quizId);
	public Personne getPersonneById(Long PersonneId);
	public void deleteQuNote(Long QuestionId,Long QuizId);
	public void updateQuiz(Quiz quiz);
	public void addQuNote(QuNote Qunt);
	public Question getQuestionById(Long questionId);
	public void deleteQuestion(Long questionId);
	public void addQuestion(Question question);
	public void addReponse(Reponse reponse);
	public void updateQuestion(Question question);
	public void deleteReponse(Long ReponseId);
	/*
	//question
	
	
	public void updateQuestion(Question question);
	//reponse
	
    public void deleteReponse(Long reponseId);
	public void addReponseToQuestion(Long questionId, Reponse reponse);
	*/
	
}
