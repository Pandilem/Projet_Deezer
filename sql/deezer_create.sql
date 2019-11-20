create table utilisateur
(
    ID_Utilisateur      int  not null
        primary key auto_increment,
    Nom_Utilisateur     text not null,
    Prenom_Utilisateur  text not null,
    Artiste_Utilisateur text not null,
    Titre_Utilisateur   text not null
);

create table historique
(
    ID_Historique             int not null
        primary key auto_increment,
    Artiste_Historique        int not null,
    Album_Historique          int not null,
    Titre_Historique          int not null,
    ID_Utilisateur_Appartenir int not null,
    constraint HISTORIQUE_Utilisateur_FK
        foreign key (ID_Utilisateur_Appartenir) references utilisateur (ID_Utilisateur)
);
