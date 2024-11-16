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
}
