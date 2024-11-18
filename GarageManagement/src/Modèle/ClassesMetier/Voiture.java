package Modèle.ClassesMetier;

import javax.swing.*;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Voiture extends Vehicule
{
    public Voiture(String marque, String modele, String puissance, String transmission, String pays, int annee, String image)
    {
        super(marque, modele, puissance, transmission, pays, annee, image, "Voiture");
    }

    public Voiture(int id,String marque, String modele, String puissance, String transmission, String pays, int annee, String image)
    {
        super(id, marque, modele, puissance, transmission, pays, annee, image, "Voiture");
    }

}
