package metier.services;
import java.util.List;

import jakarta.ejb.Remote;
import metier.entities.*;
@Remote
public interface IQuizRemote {
	
	public Personne authenticate(String login ,String password);
	public void SignUp(String nom, String prenom, String login, String password,char type);
	//quiz
	/*public List<Quiz> getAllQuiz();
	public Quiz getQuizById(Long quizId);
	public void addQuiz(Quiz quiz);
	public void updateQuiz(Quiz quiz);
	public void deleteQuiz(Long quizId);
	//question
	public List<Question> getAllQuestionsByQuizId(Long quizId);
	public Quiz getQuestionById(Long questionId);
	public void updateQuestion(Question question);
	public void deleteQuestion(Long questionId);
	public void addQuestionToQuiz(Long quizId, Question question);
	//reponse
	public List<Reponse> getAllReponsesByQuestionId(Long questionId);
	public Quiz getReponseById(Long reponseId);
    public void updateReponse(Reponse reponse);
    public void deleteReponse(Long reponseId);
	public void addReponseToQuestion(Long questionId, Reponse reponse);
	*/
	
}
