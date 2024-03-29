package deezer.api;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import deezer.jdbc.Parametres;
import deezer.jdbc.RequetesHistorique;
import deezer.jdbc.RequetesUtilisateur;

public class Requetes {
	private int idTitre;
	private static int idArtiste;
	private int idAlbum;

	public Requetes(int idTitre, int idArtiste, int idAlbum) {
		this.idTitre = idTitre;
		this.idArtiste = idArtiste;
		this.idAlbum = idAlbum;
	}

	public int getIdTitre() {
		return idTitre;
	}

	public void setIdTitre(int idTitre) {
		this.idTitre = idTitre;
	}

	public int getIdArtiste() {
		return idArtiste;
	}

	public void setIdArtiste(int idArtiste) {
		this.idArtiste = idArtiste;
	}

	public int getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}
	
	
	private static final String jsonFileName = "sample.json";

	public static void chercher(String mot) throws MalformedURLException, IOException {

		String url = "https://api.deezer.com/search?q=" + mot;

		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
		writeJson(jsonText);

		// JSONObject jsonComplet = new JSONObject(jsonText);

	}

	/**
	 * Ecrit le contenu d'un string dans le fichier jsonFileName.
	 * 
	 * @param jsonText
	 */
	public static void writeJson(String jsonText) {
		BufferedWriter bw;

		try {
			bw = new BufferedWriter(new FileWriter(jsonFileName));
			bw.write(jsonText);
			bw.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * recherche un titre de musique et l'artiste dans l'API 
	 * récupère et parcours un fichier Json pour récupérer les Id titre, Id artiste
	 * affiche "pas de résultat" si la requête est vide 
	 * affiche "pas de résultat exact" si le titre et l'artiste ne sont pas strictement identiques
	 * affiche le titre, l'artiste et l'album recherché et les ajoute dans l'historique avec l'id utilisateur
	 * @param titre : nom de la musique recherchée
	 * @param artiste : nom de l'artiste ou du groupe
	 * @param idUser : id de l'utilisateur
	 * @throws IOException
	 * @throws SQLException
	 */
	
	//TODO Modifier la méthode pour qu'elle retourne un objet de classe musique.
	public static void rechercheTitre(String titre, String artiste, int idUser) throws IOException, SQLException {

		String titreTemp = titre.replaceAll(" ", "%20");
		String artisteTemp = artiste.replaceAll(" ", "%20");

		String url = "https://api.deezer.com/search?q=artist:\"" + artisteTemp + "\"%20track:\"" + titreTemp + "\"";

		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
		writeJson(jsonText);

		JSONObject complet = new JSONObject(jsonText);
		JSONArray data = complet.getJSONArray("data");
		int compteur=0;
		
		//vérifie que le Json n'est pas vide
		if (data.isEmpty() == false) {
			
			for (Object object : data) {
				JSONObject currentObject = (JSONObject) object;
				String titreChanson = currentObject.getString("title");
				int idTitre = currentObject.getInt("id");

				JSONObject artisteObject = currentObject.getJSONObject("artist");
				String nomArtiste = artisteObject.getString("name");
				int idArtiste = artisteObject.getInt("id");

				JSONObject albumObject = currentObject.getJSONObject("album");

				int idAlbum = albumObject.getInt("id");

				if (titreChanson.toLowerCase().equals(titre.toLowerCase())
						&& nomArtiste.toLowerCase().equals(artiste.toLowerCase())) {
//TODO supprimer l'ajout dans l'historique
					RequetesHistorique.ajouterHistorique(idArtiste, idTitre, idAlbum, idUser);// 3 à modifier
					lireId(idTitre);
					break;
				}
				compteur++;
				
			}
			//si le nombre d'éléments parcourus est égal au nombre d'éléments dans le Json 
			if (data.length()==compteur) {
				System.out.println("Pas de résultat exact trouvé");
			}
			
		} else {
			System.out.println("Pas de résultat");
		}
	}
	
	/**
	 * recherche dans l'API l'id correspondant au titre de la musique 
	 * imprime le String du titre de la musique, le String du nom de l'artiste, le String du nom de l'album correspondant à l'id titre de la musique
	 * * @param id
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void lireId(int id) throws MalformedURLException, IOException {
		String url = "https://api.deezer.com/track/" + id;

		String jsonText = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
		writeJson(jsonText);

		JSONObject complet = new JSONObject(jsonText);
		String titre = complet.getString("title");
		JSONObject album = complet.getJSONObject("album");
		String title = album.getString("title");
		JSONObject artist = complet.getJSONObject("artist");
		String artiste = artist.getString("name");
		System.out.println("la chanson : " + titre + ". L'artiste : " + artiste + ". L'album : " + title);

	}
	/**  
	 * @param args
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws MalformedURLException, IOException, SQLException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Quels sont la musique et l'artiste recherchés?\nTitre de la musique :");
		String titreMusique = sc.nextLine();
		System.out.println("artiste de la musique :");
		String artisteMusique = sc.nextLine();
		rechercheTitre(titreMusique, artisteMusique, 11);
		// lireId(1104580);
	}
}

