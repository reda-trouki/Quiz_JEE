package com.metier;

import java.io.Serializable;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import metier.entities.*;
import metier.services.*;

@Named("PerssonneBean")
@SessionScoped
public class PerssonneBean implements Serializable {
	@EJB
	private IQuizLocale localQuiz;
	private String nom;
	private String prenom;
	private String login;
	private String password;
	private char type;
	public PerssonneBean() {
		
	}
	public String authenticate() {
		Personne perso = localQuiz.authenticate(login, password);
		if(perso!=null) {
			if(perso.getClass().getSimpleName().equalsIgnoreCase("Apprenant")) return "homeA";
			else return "homeP";
		}
		return "index.xhtml?notFound=true";
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
	
	
}
