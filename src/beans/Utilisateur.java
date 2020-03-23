package beans;

public class Utilisateur {
	private String login;
	private int id;
	private String prenom;
	private String nom;
	private String numero;
	private String profil;
	private int solde;

	

	public Utilisateur(String login, int id, String prenom, String nom, String numero, String profil, int solde) {
		super();
		this.login = login;
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.numero = numero;
		this.profil = profil;
		this.solde = solde;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public String getProfil() {
		return profil;
	}



	public Utilisateur(String login, int id, String prenom, String nom) {
		this.login = login;
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}



	public void setProfil(String profil) {
		this.profil = profil;
	}



	public int getSolde() {
		return solde;
	}



	public void setSolde(int solde) {
		this.solde = solde;
	}



	public Utilisateur() {
	}

}
