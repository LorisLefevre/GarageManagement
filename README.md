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
