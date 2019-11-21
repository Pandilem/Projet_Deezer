Description du projet Deezer :

On lance l'application et le menu décrit ci-après s'affiche.
Ce menu se compose de : 
- "Connexion" qui permet de se connecter, de modifier, de supprimer ou de créer un compte utilisateur dans la table "utilisateur"
- "Rechercher une musique" qui permet de rechercher une musique depuis l'API Deezer et instancier la table "historique"
- "Voir l'historique" qui permet de consulter l'historique de l'utilisateur dans la table "historique"
- "Quitter" qui permet de quitter le menu. 


Description de l'implémentation technique du projet :

Présentation de la classe "Requetes" du package api
Cette classe permet de faire l'ensemble des requetes vers l'ApiDeezer.

Méthode "chercher" permet de chercher un élément dans l'API via un chaîne de caractères.
Méthode "writeJson" permet d'écrire un fichier Json à partir duquel les recherches pourront êtres réalisées.
Méthode "rechercheTitre" permet de rechercher avec l'artiste et le titre, via le fichier Json, l'ID correspondant et rajoute la recherche 
dans l'historique.
Méthode "lireId" permet grâce à l'id du titre de la musique permet de récupérer l'ensemble des chaines de caractères correspondant au titre, à l'album et l'artiste.  

Description du "menu" :

La classe menu contient un menu principal à partir duquel on peut naviguer dans différents sous-menus.
L'affichage du menu se fait via la variable currentMenu qui est modifiée en fonction des choix du l'utilisateur.
Le menu principal est composé 4 propositions dont 2 sous-menus (profil/connexion et recherche).
Selon le choix fait par l'utilisateur, le menu va appeler les méthodes associées.


