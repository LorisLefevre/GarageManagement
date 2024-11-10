package Modèle.ClassesMetier;

import javax.swing.*;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Voiture extends Vehicule
{
    public Voiture(String marque, String modele, String puissance, String transmission, String pays, int annee, String image)
    {
        super(marque, modele, puissance, transmission, pays, annee, image, "Voiture"); // Le type est "Voiture"
    }

    // Vous pouvez ajouter des méthodes spécifiques à la classe Voiture ici si nécessaire.
    public static void main(String[] args)
    {
        Voiture voiture1 = new Voiture("Nissan", "GTR", "600 ch", "Automatique", "Japon", 2021, "C:/Users/Loris/NissanGTR.jpg");
        Voiture voiture2 = new Voiture("Toyota", "Supra", "335 ch", "Automatique", "Japon", 2021, "C:/Users/Loris/ToyotaSupra.jpg");

        // Liste de véhicules
        List<Vehicule> vehicules = new ArrayList<>();
        vehicules.add(voiture1);
        vehicules.add(voiture2);

        // Affichage des véhicules dans la liste
        for (Vehicule vehicule : vehicules)
        {
            if (vehicule.getType().equals("Voiture")) // Filtre par type "Voiture"
            {
                vehicule.afficher();
            }
        }
    }
}
