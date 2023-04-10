package metier.services;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import metier.entities.Apprenant;
import metier.entities.Personne;
import metier.entities.Poseur;

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

}
//from User where username = :username and password = :password