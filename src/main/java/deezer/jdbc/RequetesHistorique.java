package deezer.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

public class RequetesHistorique {
	/**
	 * Permet d'ajouter une musique avec l'artiste, l'album et l'Id de la personne qui à fait la recherche
	 * @param idArtiste artiste de la musique
	 * @param idTitre nom de la musique
	 * @param idAlbum nom de l'album de la musique
	 * @param idUser utilisateur qui à fait la recherche
	 */
	//TODO remplacer les paramètres de ajouterHistorique par une musique : void ajouterHistorique(Utilisateur addUser) {
	public static void ajouterHistorique(int idArtiste, int idTitre, int idAlbum, int idUser) {
		try {

			PreparedStatement stmt = Parametres.getConnexion().prepareStatement(
					"INSERT INTO historique (Artiste_Historique, Album_Historique, Titre_Historique, "
					+ "ID_Utilisateur_Appartenir) values(?,?,?,?)");

			stmt.setInt(1, idArtiste);
			stmt.setInt(2, idAlbum);
			stmt.setInt(3, idTitre);
			stmt.setInt(4, idUser);

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			System.out.println("Erreur " + e);
		}
	}
	/**
	 * Permet de supprimer une ligne dans l'historique
	 * @param id id de l'historique a supprimer
	 */
	public static void supprimerHistorique(int id) {
		try {

			PreparedStatement stmt = Parametres.getConnexion()
					.prepareStatement("delete from historique where ID_Historique=" + id + ";");

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			System.out.println("Erreur " + e);
		}
	}
	/**
	 * Permet de rechercher l'historique d'un utilisateur grâce a son id
	 * @param id id de l'utilisateur qu'on recherche
	 * @return
	 */
	public static boolean rechercherUtilHisto(int id) {
		boolean resultatRequete=false;
		try {

			Statement stmt = Parametres.getConnexion().createStatement();
			String requetes ="SELECT * from historique where ID_Utilisateur_Appartenir=" + id + ";";
			
			ResultSet rs = stmt.executeQuery(requetes);
			resultatRequete =rs.first();
			
			if (resultatRequete==true) {
				
				rs = stmt.executeQuery(requetes);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				
				while (rs.next()) {
					for (int i = 1; i <= columnsNumber; i++) {
						String columnValue = rs.getString(i);
						String columnName = rsmd.getColumnName(i);
						System.out.print(MessageFormat.format("<{0}:{1}>\t\t", columnName, columnValue));
					}
					System.out.println();
			}
				
					
			stmt.close();
			}else {
				System.out.println("Pas d'historique pour l'utilisateur "+id);
			}
		} catch (SQLException e) {
			System.out.println("Erreur " + e);
		}
		return resultatRequete;
	}
	
	/**
	 * Permet de supprimer l'historique d'un utilisateur
	 * @param id
	 */
	public static void supprimerUtilHisto(int id) {
		try {

			PreparedStatement stmt = Parametres.getConnexion()
					.prepareStatement("delete from historique where ID_Utilisateur_Appartenir=" +id+ ";");
			
				

			stmt.execute();	
			stmt.close();
			System.out.println("Supression de l'historique ci-dessus");
		} catch (SQLException e) {
			System.out.println("Erreur " + e);
		}
	}
	
	public static void main(String[] args) throws SQLException {
		//TODO écrire tous les tests
		//supprimerHistorique(3);
		// Parametres.afficherTable("historique");
		/*
		 * ajouterHistorique( 163, 1104580, 119139, 3); afficherTable("historique");
		 */
		//rechercherUtilHisto(1);
		//rechercherUtilHisto(2);
		supprimerUtilHisto(4);

	}

}
