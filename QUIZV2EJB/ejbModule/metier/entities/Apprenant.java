package metier.entities;

import java.io.Serializable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("APPR")
public class Apprenant extends Personne implements Serializable {

	public Apprenant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Apprenant(String nom, String prenom, String login, String password) {
		super(nom, prenom, login, password);
	}
	
}
