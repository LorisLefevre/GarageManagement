package Modèle.ClassesMetier;

import javax.swing.*;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Vehicule
{
    private String marque;
    private String modele;
    private String puissance;
    private String transmission;
    private String pays;
    private int annee;
    private String image;
    private String type;

    public Vehicule(String marque, String modele, String puissance, String transmission, String pays, int annee, String image, String type)
    {
        this.marque = marque;
        this.modele = modele;
        this.puissance = puissance;
        this.transmission = transmission;
        this.pays = pays;
        this.annee = annee;
        this.image = image;
        this.type = type;
    }

    // Getters et Setters
    public String getMarque()
    {
        return marque;
    }
    public void setMarque(String marque)
    {
        this.marque = marque;
    }

    public String getModele()
    {
        return modele;
    }
    public void setModele(String modele)
    {
        this.modele = modele;
    }

    public String getPuissance()
    {
        return puissance;
    }
    public void setPuissance(String puissance)
    {
        this.puissance = puissance;
    }

    public String getTransmission() { return transmission; }
    public void setTransmission(String transmission)
    {
        this.transmission = transmission;
    }

    public String getPays()
    {
        return pays;
    }
    public void setPays(String pays)
    {
        this.pays = pays;
    }

    public int getAnnee()
    {
        return annee;
    }
    public void setAnnee(int annee)
    {
        if(annee >= 1885 && annee <= 2025)
        {
            this.annee = annee;
        }

        else
        {
            throw new IllegalArgumentException("L'année du véhicule est invalide");
        }

    }

    public String getImage()
    {
        return image;
    }
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    // Méthode toString
    @Override
    public String toString()
    {
        return "Vehicule{" +
                "marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", puissance='" + puissance + '\'' +
                ", transmission='" + transmission + '\'' +
                ", pays='" + pays + '\'' +
                ", annee=" + annee +
                ", type='" + type + '\'' +
                '}';
    }

    // Méthode pour afficher les informations du véhicule
    public void afficher()
    {
        System.out.println("Marque : " + marque);
        System.out.println("Modèle : " + modele);
        System.out.println("Puissance : " + puissance);
        System.out.println("Transmission : " + transmission);
        System.out.println("Pays : " + pays);
        System.out.println("Année : " + annee);
        System.out.println("Type : " + type); // Affichage du type du véhicule
    }

    public static void main(String[] args)
    {
        // Exemple de création des véhicules
        Voiture voiture1 = new Voiture("Nissan", "GTR", "600 ch", "Automatique", "Japon", 2021, "null");
        Voiture voiture2 = new Voiture("Toyota", "Supra", "335 ch", "Automatique", "Japon", 2021, "null");
        Moto moto1 = new Moto("Yamaha", "R1", "200 ch", "Manuelle", "Japon", 2019, "null");
        Camionnette camionnette1 = new Camionnette("Ford", "Transit", "130 ch", "Automatique", "États-Unis", 2020, "null");
        Camion camion1 = new Camion("Volvo", "FH16", "750 ch", "Automatique", "Suède", 2022, "null");

        // Création de la liste de véhicules
        List<Vehicule> vehicules = new ArrayList<>();
        vehicules.add(voiture1);
        vehicules.add(voiture2);
        vehicules.add(moto1);
        vehicules.add(camionnette1);
        vehicules.add(camion1);

        // Liste pour chaque type de véhicule
        List<Vehicule> voitures = new ArrayList<>();
        List<Vehicule> motos = new ArrayList<>();
        List<Vehicule> camionnettes = new ArrayList<>();
        List<Vehicule> camions = new ArrayList<>();

        // Séparation des véhicules par type
        for (Vehicule vehicule : vehicules)
        {
            if (vehicule.getType().equals("Voiture"))
            {
                voitures.add(vehicule);
            }
            else if (vehicule.getType().equals("Moto"))
            {
                motos.add(vehicule);
            }
            else if (vehicule.getType().equals("Camionnette"))
            {
                camionnettes.add(vehicule);
            }
            else if (vehicule.getType().equals("Camion"))
            {
                camions.add(vehicule);
            }
        }

        // Affichage des véhicules par type
        if (!voitures.isEmpty())
        {
            System.out.println("\nVOITURES\n");
            for (Vehicule voiture : voitures)
            {
                voiture.afficher();
            }
        }

        if (!motos.isEmpty())
        {
            System.out.println("\nMOTOS\n");
            for (Vehicule moto : motos)
            {
                moto.afficher();
            }
        }

        if (!camionnettes.isEmpty())
        {
            System.out.println("\nCAMIONNETTES\n");
            for (Vehicule camionnette : camionnettes)
            {
                camionnette.afficher();
            }
        }

        if (!camions.isEmpty())
        {
            System.out.println("\nCAMIONS\n");
            for (Vehicule camion : camions)
            {
                camion.afficher();
            }
        }
    }
}
