package deezer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import deezer.api.Requetes;
import deezer.jdbc.Parametres;
import deezer.jdbc.RequetesHistorique;
import deezer.jdbc.RequetesUtilisateur;
import deezer.model.Utilisateur;

public class Menu {
	public static Scanner scan = new Scanner(System.in);
	public static String currentMenu = "Accueil";
	public static int idUtilisateur=1;
	/**
	 * Affiche le menu correspondant à la variable currendMenu
	 */
	public static void afficherMenu() {
		if (currentMenu.contentEquals("Accueil")) {
			System.out.println(
					"- 1 Connexion\n" 
			+ "- 2 Rechercher une musique\n" 
			+ "- 3 Voir l'historique général\n" 
			+ "- 4 Afficher votre historique\n"
			+ "- 5 Quitter");
		}
		if (currentMenu.contentEquals("Connecte")) {
			System.out.println(
					"- 1 Profil\n" 
			+ "- 2 Rechercher une musique\n" 
			+ "- 3 Voir l'historique général\n" 
			+ "- 4 Afficher votre historique\n"
			+ "- 5 Quitter");
		}
		if (currentMenu.contentEquals("Profil")) {
			System.out.println("- 1 Modifier Profil\n" 
					+ "- 2 Supprimer utilisateur\n" 
					+ "- 3 Accueil\n"
				+ "- 4 Deconnexion\n");
		}
		if (currentMenu.contentEquals("Recherche")) {
			System.out.println("- 1 Recherche du nom de la musique et l'artiste/groupe\n" 
					+ "- 2 Recherche Globale\n" 
					+ "- 3 Rechercher le titre et arstiste en favoris\n"
				+ "- 4 Accueil\n");
		}
		
	}
	/**
	 * Permet à l'utilisateur de créer un utilisateur dans la base de données 
	 * ou choisi un utilisateur déjà existant en base
	 * @throws SQLException
	 */
	public static void choixConnexion() throws SQLException {
		System.out.println("Avez-vous déjà un compte ? oui/non");
		boolean c = false;
		String choix = scan.nextLine();
		if (choix.contentEquals("non")||choix.contentEquals("n")) {
			
			System.out.println("Veuillez renseigner vos informations");
			System.out.print("Ecrivez votre nom: ");
			String nom = scan.nextLine();
			System.out.print("Ecrivez votre prenom: ");
			String prenom = scan.nextLine();
			System.out.print("Ecrivez votre artiste/groupe préféré: ");
			String artiste = scan.nextLine();
			System.out.print("Ecrivez votre musique préféré: ");
			String titre = scan.nextLine();
			Utilisateur newUtilisateur = new Utilisateur(nom, prenom, artiste, titre);
			RequetesUtilisateur.ajouterUtilisateur(newUtilisateur);
		}
		Parametres.afficherTable("utilisateur");
		System.out.println("Quel est votre ID ?");
		idUtilisateur = Integer.parseInt(scan.nextLine());
	}
	/**
	 * Permet de se déplacer dans le sous menu profil
	 * @throws SQLException
	 */
	public static void menuProfil() throws SQLException{
		
		int choix;
		boolean flag = true;
		while (flag == true) {
			afficherMenu();
			System.out.println("\nQuel est votre choix dans profil ?");
			choix = Integer.parseInt(scan.nextLine());
			switch (choix) {
			case 1:
				RequetesUtilisateur.modifUtilisateur(idUtilisateur);
				break;
			case 2:
				RequetesUtilisateur.supprimerUtilisateur(idUtilisateur);
				idUtilisateur=1;
				currentMenu = "Accueil";
				flag=false;
				break;
			case 3:
				System.out.println("Retour à l'accueil");
				currentMenu = "Connecte";
				flag = false;
				break;
			case 4:
				System.out.println("Deconnexion");
				currentMenu = "Accueil";
				idUtilisateur=1;				
				flag = false;
				break;
			default:
				System.out.println("Veuillez choisir un choix dans le menu");
				break;
			}
		}

	}

	/**
	 * Permet de se déplacer dans le sous menu recherche
	 * @throws SQLException
	 */
	public static void rechercher() throws SQLException, IOException{
		int choix;
		boolean flag = true;
		while (flag == true) {
			afficherMenu();
			System.out.println("\nQuel est votre choix dans recherche?");
			choix = Integer.parseInt(scan.nextLine());
			switch (choix) {
			case 1:
				System.out.println("Quels sont la musique et l'artiste recherchés?\nTitre de la musique :");
				String titreMusique = scan.nextLine();
				System.out.println("artiste de la musique :");
				String artisteMusique = scan.nextLine();
				Requetes.rechercheTitre(titreMusique, artisteMusique, idUtilisateur);
				
				break;
			case 2:
				System.out.println("Que voulez-vous rechercher ?");
				String mot=scan.nextLine();
				Requetes.chercher(mot);
				break;
			case 3:
				Requetes.rechercheTitre(RequetesUtilisateur.infoUtilisateur(idUtilisateur).getTitre(),
						RequetesUtilisateur.infoUtilisateur(idUtilisateur).getArtiste(), idUtilisateur);
				System.out.println();
				break;
			case 4:
				System.out.println("Retour à l'accueil");
				currentMenu = "Accueil";				
				flag = false;
				break;
			default:
				System.out.println("Veuillez choisir un choix dans le menu");
				break;
			}
		}
	}
	
	/**
	 * Permet de se déplacer dans le menu
	 * @throws SQLException
	 */
	public static void choixMenu() throws IOException, SQLException {
		boolean flag = true;
		int choix;
		while (flag == true) {
			afficherMenu();
			System.out.println("\nQuel est votre choix ?");
			choix = Integer.parseInt(scan.nextLine());
			switch (choix) {
			case 1:
				if (currentMenu.contentEquals("Connecte")) {
					currentMenu = "Profil";
					menuProfil();
				} else {
					choixConnexion();
					currentMenu = "Connecte";
				}
				break;
			case 2:
				currentMenu = "Recherche";
				rechercher();
				break;
			case 3:
				Parametres.afficherTable("historique");
				break;
			case 4:
				System.out.println("Historique de l'utilisateur : "+idUtilisateur);
				RequetesHistorique.rechercherUtilHisto(idUtilisateur);
				
				break;
			case 5:
				System.out.println("Vous quittez Deezer");
				flag = false;
				break;
			default:
				System.out.println("Veuillez choisir un choix dans le menu");
				break;
			}
		}
		System.out.println("Au revoir");

	}

	public static void main(String[] args) throws IOException, SQLException {
		choixMenu();
	}
}
