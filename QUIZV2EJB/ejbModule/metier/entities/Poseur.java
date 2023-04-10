package metier.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("POSE")
public class Poseur extends Personne implements Serializable {

	public Poseur() {
		super();
		
	}

	public Poseur(String nom, String prenom, String login, String password) {
		super(nom, prenom, login, password);
	}
	
}
