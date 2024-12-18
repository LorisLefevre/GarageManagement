Cette application a pour but de gérer des véhicules pouvant être de type voiture, moto, camionnette ou camion.

On connait le type de véhicule, la marque, le modèle, la puissance, la transmission, le pays, l'année et une image du véhicule.

Nous avons 2 moyens de stocker un vehicule :
->Via un fichier texte
->Via une base de données

L'utilisateur a la possibilité de changer de mode, pouvant travailler sur Fichier texte ou sur Base de données librement, sans devoir relancer l'application

Nous avons 3 fenêtres

-> LoginWindow : Cette fenêtre sert à entrer un nom d'utilisateur et un mot de passe. À partir de là, on peut se connecter si notre mot de passe (qui a été crypté) est correct et si on a bien été ajouté. On peut également se déconnecter.

->GarageWindow : C'est dans cette fenêtre que vous gèrerez les véhicules. Vous aurez donc la liste des véhicules enregistrés dans le fichier texte ou dans la base de données. Vous pourrez ajouter, supprimer, modifier et voir vos véhicules à partir de là. 

->VehiculeInformationWindow : C'est dans cette fenêtre que vous verrez les inforamtions de chaque véhicule ainsi que l'image correspondant en taille réelle.

Ce projet ayant pour but d'utiliser 3 designs patterns, voici ceux que j'ai implémentés :
  ->Singleton 
  ->Factory
  ->Strategy

  En l'état actuel des choses, l'application est terminée pour ce qu'elle est censée faire. 

  À présent, voici tous les cas où un design pattern a été implémenté :
  ->SINGLETON :
    1.Dans la classe CreationTables qui est une classe servant à créer/supprimer des tables dans une base de données.
    2.Dans la classe Requetes qui est une classe servant à effectuer des requêtes sur la table "vehicules".
    3.Dans la classe Garage qui est une classe servant de DAO pure. Elle sert à l'ajout, la supression, la modification, l'affichage, le chargement, la recherche et le formatage de véhicules. 
    4.Dans la classe GestionFichier qui est une classe qui sert à manipuler le fichier "vehicles.txt", appelé par la classe Garage lors des différents cas d'utilisation.
    5.Dans la classe FormulaireVehicule qui est une classe qui sert à avoir une mini interface graphique servant à encoder les données du véhicule et à ajouter l'image.
    6.Dans la classe GarageWindow, dont le but est déjà expliqué.
    7.Dans la classe LoginWindow, dont le but est déjà expliqué.
    8.Dans la classe VehiculeInformationWindow, dont le but est déjà expliqué.
    9.Dans la classe ChoixUtilisateur qui sert à basculer d'un mode de travail à l'autre.
    10.Dans la classe MethodesBoutonsGarageWindow qui contient les méthodes des boutons lorsque l'on clique sur l'un d'eux et qu'on travaille sur le fichier texte.
    11.Dans la classe MethodesBoutonsGarageWindow qui contient les méthodes des boutons lorsque l'on clique sur l'un d'eux et qu'on travaille sur la base de données.
    12.Dans la classe MethodesGarageWindow qui contient les méthodes de GarageWindow non-liées aux boutons.
  ->FACTORY :
    1.La classe VehiculeFactory est une factory servant à créer un véhicule en fonction du type choisi qui est compris entre Voiture, Moto, Camionnette et Camion.
  ->STRATEGY
    1.Dans l'interface StrategieModeTravail qui contient le prototype des méthodes appelées pour la manipulation de l'application, c'est-à-dire lorsque l'on clique sur les boutons.
    2.Dans la classe StrategieTravailFichierTexte qui implémente les méthodes de l'interface, en version fichier texte.
    3.Dans la classe StrategieTravailBaseDeDonnees qui implémente les méthodes de l'interface, en version base de données.

Maintenant, voyons dans quel cas chaque design pattern a été utilisé. Je parle ici en termes d'appel de méthode :
->SINGLETON :
  1.Dans le contrôleur lorsque l'on est dans un LOGIN. On récupère l'instance de GarageWindow pour pouvoir ensuite lui setter le contrôleur(rendre les boutons fonctionnels)
  2.Dans la classe GarageWindow lorsque l'on clique sur l'un des boutons. Lorsque l'on clique sur un bouton, on appelle la méthode correspondante de la classe ChoixUtilisateur tout en récupérant son instance.
  3.Toujours dans la classe GarageWindow. Une fois qu'on a changé de mode de travail après avoir affiché un véhicule via la VehiculeInformationWindow, on récupére l'instance de la dernière classe mentionnée pour     la fermer histoire de ne pas avoir un véhicule affcihé à partir de la base de données alors que l'on vient de passer en mode FICHIER_TEXTE.
  4.Dans la classe LoginWindow. Lorsque l'on se connecte, on récupère l'instance de GarageWindow(ou plutôt on en crée un ici), ainsi que l'instance de ChoixUtilisateur pour choisir un mode d'ouverture.
  5.Toujours dans la classe LoginWindow. Lorsque que l'on se déconnecte, on fait un reset de GarageWindow, en gros mettre l'instance à null, la fermer et tout vider. Et on récupère l'instance de 
    VehiculeInformationWindow pour faire pareil.
  6.Dans la classe ChoixUtilisateur. Lorsque l'on change de mode, on récupère l'instance de GarageWindow et on change le label du bouton qui sert à changer de mode pour que l'utilisateur sache dans quel type 
    d'enregistrement il se trouve.
  7.Toujours dans la classe ChoixUtilisateur, on récupère l'instance de GarageWindow pour afficher un message d'erreur si un problème est survenu
->FACTORY :
->STRATEGY :
