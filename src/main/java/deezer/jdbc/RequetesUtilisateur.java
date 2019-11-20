package deezer.jdbc;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import deezer.api.Requetes;
import deezer.model.Utilisateur;

public class RequetesUtilisateur {

	public static void ajouterUtilisateur(Utilisateur utilisateur) {
		try {

			PreparedStatement stmt = Parametres.getConnexion().prepareStatement(
					"INSERT INTO utilisateur (Nom_Utilisateur, Prenom_Utilisateur, Artiste_Utilisateur, Titre_Utilisateur) values(?,?,?,?)");

			stmt.setString(1, utilisateur.getNom());
			stmt.setString(2, utilisateur.getPrenom());
			stmt.setString(3, utilisateur.getArtiste());
			stmt.setString(4, utilisateur.getTitre());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			System.out.println("Erreur " + e);
		}
	}

	public static Utilisateur infoUtilisateur(int id) throws SQLException {

		PreparedStatement stmt = Parametres.getConnexion()
				.prepareStatement("Select * from utilisateur where ID_Utilisateur=?");
		stmt.setInt(1, id);
		ResultSet result = stmt.executeQuery();
		result.next();

		Utilisateur currentUser = new Utilisateur();
		currentUser.setArtiste(result.getString("Artiste_Utilisateur"));
		currentUser.setTitre(result.getString("Titre_Utilisateur"));
		currentUser.setNom(result.getString("Nom_Utilisateur"));
		currentUser.setPrenom(result.getString("Prenom_Utilisateur"));
		currentUser.setId(id);

		return currentUser;
	}

	public static void supprimerUtilisateur(int id) throws SQLException {
		try {
			if (RequetesHistorique.rechercherUtilHisto(id) == true) {
				RequetesHistorique.supprimerUtilHisto(id);

			}

			PreparedStatement stmt = Parametres.getConnexion()
					.prepareStatement("delete from utilisateur where ID_Utilisateur=" + id + ";");

			stmt.execute();
			stmt.close();
			System.out.println("Suppression de l'utilisateur " + id);

		} catch (SQLException e) {
			System.out.println("Erreur " + e);
		}
	}

	public static void modifUtilisateur(int id) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Vous allez modifier le titre et l'artiste/groupe favoris");
		System.out.println("Nouvel artiste/groupe :");
		String artiste = scan.nextLine();
		System.out.println("Nouveau titre de musique :");
		String titre = scan.nextLine();

		PreparedStatement stmt = Parametres.getConnexion().prepareStatement(
				"Update utilisateur set Artiste_Utilisateur=?, Titre_Utilisateur=? where ID_Utilisateur=?");
		stmt.setString(1, artiste);
		stmt.setString(2, titre);
		stmt.setInt(3, id);
		stmt.execute();
		stmt.close();


	}

	public static void main(String[] args) throws SQLException, IOException {
		// RequetesHistorique.supprimerHistorique(1);
		//supprimerUtilisateur(5);
		// Parametres.afficherTable("utilisateur");
		// test ajout utilisateur
		/*
		 * Utilisateur Jacques = new Utilisateur( "Nono", "Jacques", "U2", "One");
		 * ajouterUtilisateur(Jacques); afficherTable("utilisateur");
		 */
		// Requetes.rechercheTitre(favoris(11).getTitre(),favoris(11).getArtiste(), 11);
	}
}
