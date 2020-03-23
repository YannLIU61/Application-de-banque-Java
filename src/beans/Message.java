package beans;

public class Message {
	private String nom;
	private String prenom;
	private String sujet;
	private String corps;

	public Message(String nom, String prenom, String sujet, String corps) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sujet = sujet;
		this.corps = corps;
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

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getCorps() {
		return corps;
	}

	public void setCorps(String corps) {
		this.corps = corps;
	}

	public Message() {
	}

}
