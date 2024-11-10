package Modèle.ClassesMetier;

import java.awt.Image;

public class Camionnette extends Vehicule
{
    // Constructeur de la classe Camionnette qui appelle le constructeur de Vehicule
    public Camionnette(String marque, String modele, String puissance, String transmission, String pays, int annee, String image)
    {
        super(marque, modele, puissance, transmission, pays, annee, image, "Camionnette"); // Le type est "Camionnette"
    }

    // Vous pouvez ajouter des méthodes spécifiques à la classe Camionnette ici si nécessaire.
}
