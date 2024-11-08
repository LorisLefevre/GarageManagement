package Modèle.ClassesMetier;

import java.awt.Image;

public class Moto extends Vehicule
{
    // Constructeur de la classe Moto qui appelle le constructeur de Vehicule
    public Moto(String marque, String modele, String puissance, String transmission, String pays, int annee, Image image)
    {
        super(marque, modele, puissance, transmission, pays, annee, image, "Moto"); // Le type est "Moto"
    }

    // Vous pouvez ajouter des méthodes spécifiques à la classe Moto ici si nécessaire.
}
