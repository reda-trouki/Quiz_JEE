package metier.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Quiz implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;
	private double note;
	private Long code;
	@ManyToOne
	private Poseur poseur;
	@OneToMany(mappedBy="quiz")
	private List<QuNote> qunotes=new ArrayList<QuNote>();
	

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public Quiz(String libelle, double note, Long code, Poseur poseur) {
		super();
		this.libelle = libelle;
		this.note = note;
		this.code = code;
		this.poseur = poseur;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}
	public Poseur getPoseur() {
		return poseur;
	}

	public void setPoseur(Poseur poseur) {
		this.poseur = poseur;
	}



	public Long getCode() {
		return code;
	}



	public void setCode(Long code) {
		this.code = code;
	}



	public List<QuNote> getQunotes() {
		return qunotes;
	}



	public void setQunotes(List<QuNote> qunotes) {
		this.qunotes = qunotes;
	}
	

}
