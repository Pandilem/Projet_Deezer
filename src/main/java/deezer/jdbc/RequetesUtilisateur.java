package deezer.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import deezer.model.Utilisateur;

public class RequetesUtilisateur {

	/*public static Connection getConnexion()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Parametres.getUrl(), Parametres.getUser(),
					Parametres.getPassword());

			

		} catch (Exception e) {
			System.out.println("Erreur " + e);
		}
		return con;
	
	}*/
	public static void ajouterUtilisateur(Utilisateur utilisateur) {
		try {

			PreparedStatement stmt = Parametres.getConnexion().prepareStatement("INSERT INTO utilisateur (Nom_Utilisateur, Prenom_Utilisateur, Artiste_Utilisateur, Titre_Utilisateur) values(?,?,?,?)");
			
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
	public static void supprimerUtilisateur(int id) {
		try {
			
			PreparedStatement stmt = Parametres.getConnexion().prepareStatement("delete from utilisateur where ID_Utilisateur=" +id+";");
		
			stmt.execute();
			stmt.close();
			
		}catch (SQLException e){
			System.out.println("Erreur " + e);
		}
}
	

	public static void main(String[] args) throws SQLException {
		RequetesHistorique.supprimerHistorique(1);
		supprimerUtilisateur(2);
		//Parametres.afficherTable("utilisateur");
		//test ajout utilisateur
		/*Utilisateur Jacques = new Utilisateur( "Nono", "Jacques", "U2", "One");	
		ajouterUtilisateur(Jacques);
		afficherTable("utilisateur");*/
	}
}
