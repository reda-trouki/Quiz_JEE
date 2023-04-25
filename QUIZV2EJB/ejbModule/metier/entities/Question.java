package metier.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Question implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String enence;
	@OneToMany(mappedBy="question",fetch = FetchType.LAZY)
	private List<Reponse>reponses=new ArrayList<Reponse>();
	@OneToMany(mappedBy="question")
	private List<QuNote> qunotes=new ArrayList<QuNote>();

	

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(String enence) {
		super();
		this.enence = enence;

	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnence() {
		return enence;
	}
	public void setEnence(String enence) {
		this.enence = enence;
	}
	public List<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}

	public List<QuNote> getQunotes() {
		return qunotes;
	}

	public void setQunotes(List<QuNote> qunotes) {
		this.qunotes = qunotes;
	}
	
	
	

}
