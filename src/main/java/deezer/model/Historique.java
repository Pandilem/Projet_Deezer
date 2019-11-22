package deezer.model;

public class Historique {
	private int id;
	private int artiste;
	private int album;
	private int titre;
	private int idUser;
	
	public Historique(int id, int artiste, int album, int titre, int idUser) {
		
		this.id = id;
		this.artiste = artiste;
		this.album = album;
		this.titre = titre;
		this.idUser = idUser;
}
	//TODO A modifier pour retour le nom de la musique/artiste/album 
	//et pas l'ID pour les méthodes getArtiste/Album/Titre via les méthodes dans RequetesHistorique LireIDArtiste....
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArtiste() {
		return artiste;
	}

	public void setArtiste(int artiste) {
		this.artiste = artiste;
	}

	public int getAlbum() {
		return album;
	}

	public void setAlbum(int album) {
		this.album = album;
	}

	public int getTitre() {
		
		return titre;
	}

	public void setTitre(int titre) {
		this.titre = titre;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
}	
	
	