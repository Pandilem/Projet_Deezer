package deezer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import deezer.jdbc.Parametres;
import deezer.jdbc.RequetesUtilisateur;
import deezer.model.Utilisateur;

public class Menu {
	public static Scanner scan = new Scanner(System.in);
	public static String currentMenu = "Accueil";
	public static int idUtilisateur;

	public static void afficherMenu() {
		if (currentMenu.contentEquals("Accueil")) {
			System.out.println(
					"- 1 Connexion\n" 
			+ "- 2 Rechercher une musique\n" 
			+ "- 3 Voir l'historique\n" 
			+ "- 4 Quitter");
		}
		if (currentMenu.contentEquals("Connecte")) {
			System.out.println(
					"- 1 Profil\n" 
			+ "- 2 Rechercher une musique\n" 
			+ "- 3 Voir l'historique\n" 
			+ "- 4 Quitter");
		}
		if (currentMenu.contentEquals("Profil")) {
			System.out.println("- 1 Modifier Profil\n" 
					+ "- 2 Supprimer utilisateur\n" 
					+ "- 3 Accueil\n"
				+ "- 4 Deconnexion\n");
		}
		if (currentMenu.contentEquals("Recherche")) {
			System.out.println("- 1 Connaissez-vous le nom de la musique et l'artiste/groupe ?\n" 
					+ "- 2 Recherche Global\n" 
					+ "- 3 Rechercher le titre et arstiste en favoris\n"
				+ "- 4 Accueil\n");
		}
		
	}

	public static int choixConnexion() throws SQLException {
		System.out.println("Avez-vous d�j� un compte ? oui/non");
		boolean c = false;
		String choix = scan.nextLine();
		if (choix.contentEquals("oui")) {
			Parametres.afficherTable("utilisateur");
			System.out.println("Quel est votre ID ?");
			idUtilisateur = Integer.parseInt(scan.nextLine());
		} else {
			System.out.println("Veuillez renseigner vos informations");
			System.out.print("Ecrivez votre nom: ");
			String nom = scan.nextLine();
			System.out.print("Ecrivez votre prenom: ");
			String prenom = scan.nextLine();
			System.out.print("Ecrivez votre artiste/groupe pr�f�r�: ");
			String artiste = scan.nextLine();
			System.out.print("Ecrivez votre musique pr�f�r�: ");
			String titre = scan.nextLine();
			Utilisateur newUtilisateur = new Utilisateur(nom, prenom, artiste, titre);
			RequetesUtilisateur.ajouterUtilisateur(newUtilisateur);
		}
		return idUtilisateur;
	}

	public static void menuProfil() {
		
		int choix;
		boolean flag = true;
		while (flag == true) {
			afficherMenu();
			System.out.println("\nQuel est votre choix ?");
			choix = Integer.parseInt(scan.nextLine());
			switch (choix) {
			case 1:
				// modifierUtilisateur();
				currentMenu = "Connecte";
				break;
			case 2:
				// supprimer utilisateur
				idUtilisateur=0;
				currentMenu = "Accueil";
				break;
			case 3:
				System.out.println("Retour � l'accueil");
				currentMenu = "Connecte";
				flag = false;
				break;
			case 4:
				System.out.println("Deconnexion");
				currentMenu = "Accueil";
				idUtilisateur=0;				
				flag = false;
				break;
			default:
				System.out.println("Veuillez choisir un choix dans le menu");
				break;
			}
		}

	}

	public static void rechercher() {
		
		int choix;
		boolean flag = true;
		while (flag == true) {
			afficherMenu();
			System.out.println("\nQuel est votre choix ?");
			choix = Integer.parseInt(scan.nextLine());
			switch (choix) {
			case 1:
				// recherche titre et artiste
				
				break;
			case 2:
				// recherche unique
				break;
			case 3:
				//recherche avec tite et artiste utilisateur
				break;
			case 4:
				System.out.println("Retour � l'accueil");
				currentMenu = "Accueil";				
				flag = false;
				break;
			default:
				System.out.println("Veuillez choisir un choix dans le menu");
				break;
			}
		}
	}

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
