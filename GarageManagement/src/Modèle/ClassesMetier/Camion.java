package Modèle.ClassesMetier;

import java.awt.Image;

public class Camion extends Vehicule
{
    public Camion(String marque, String modele, String puissance, String transmission, String pays, int annee, String image)
    {
        super(marque, modele, puissance, transmission, pays, annee, image, "Camion");
    }
}
