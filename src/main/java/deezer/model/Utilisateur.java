package deezer.model;

public class Utilisateur {
	private int id;
	private String nom;
	private String prenom;
	private String artiste;
	private String titre;
	
	
	public Utilisateur( String nom, String prenom, String artiste, String titre) {
		super();
		this.id=0;
		this.nom = nom;
		this.prenom = prenom;
		this.artiste = artiste;
		this.titre = titre;
	}
	public Utilisateur()
	{
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getArtiste() {
		return artiste;
	}


	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}

	

}
