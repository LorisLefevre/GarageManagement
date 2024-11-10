package Modèle.ClassesMetier;

import java.awt.Image;

public class Camion extends Vehicule
{
    // Constructeur de la classe Camion qui appelle le constructeur de Vehicule
    public Camion(String marque, String modele, String puissance, String transmission, String pays, int annee, String image)
    {
        super(marque, modele, puissance, transmission, pays, annee, image, "Camion"); // Le type est "Camion"
    }

    // Vous pouvez ajouter des méthodes spécifiques à la classe Camion ici si nécessaire.
}
