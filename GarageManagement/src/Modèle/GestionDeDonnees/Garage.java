package Modèle.GestionDeDonnees;
import Modèle.ClassesMetier.*;
import Modèle.GestionDeDonnees.InterfaceGarage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Garage implements InterfaceGarage
{
    private static Garage instance;
    private List<Vehicule> listeVehicules;

    public List<Vehicule> getListeVehicules()
    {
        return this.listeVehicules;
    }

    public Garage()
    {
        listeVehicules = new ArrayList<>();
    }

    public static Garage getGarage()
    {
        if (instance == null) {
            instance = new Garage();
        }
        return instance;
    }

    @Override
    public void ajouterVehicule(Vehicule vehicule) throws IOException
    {
        listeVehicules.add(vehicule);
        List<String> lignes = GestionFichier.lireFichier();
        lignes.add(formaterVehicule(vehicule));
        GestionFichier.ecrireFichier(lignes);
    }

    @Override
    public void supprimerVehicule(String type, String marque, String modele) throws IOException
    {
        List<String> lignes = GestionFichier.lireFichier();
        List<String> lignesModifiees = new ArrayList<>();

        for (String ligne : lignes) {
            String[] attributs = ligne.split(",");
            if (attributs.length > 2 && !(attributs[0].equals(type) && attributs[1].equals(marque) && attributs[2].equals(modele))) {
                lignesModifiees.add(ligne);
            }
        }
        GestionFichier.ecrireFichier(lignesModifiees);
    }

    @Override
    public void modifierVehicule(String ancienType, String ancienneMarque, String ancienModele, Vehicule vehiculeModifie) throws IOException
    {
        List<String> lignes = GestionFichier.lireFichier();
        List<String> lignesModifiees = new ArrayList<>();

        for (String ligne : lignes)
        {
            String[] attributs = ligne.split(",");

            if (attributs.length > 2 &&
                    attributs[0].equalsIgnoreCase(ancienType) &&
                    attributs[1].equalsIgnoreCase(ancienneMarque) &&
                    attributs[2].equalsIgnoreCase(ancienModele))
            {

                lignesModifiees.add(formaterVehicule(vehiculeModifie));
            }
            else
            {

                lignesModifiees.add(ligne);
            }
        }
        GestionFichier.ecrireFichier(lignesModifiees);
    }

    @Override
    public String formaterVehicule(Vehicule vehicule)
    {
        return vehicule.getType() + "," +
                vehicule.getMarque() + "," +
                vehicule.getModele() + "," +
                vehicule.getPuissance() + "," +
                vehicule.getTransmission() + "," +
                vehicule.getPays() + "," +
                vehicule.getAnnee() + "," +
                vehicule.getImage();
    }


    public void chargerVehicules() throws IOException
    {
        listeVehicules.clear();
        listeVehicules = GestionFichier.chargerVehiculesDepuisFichier();
    }
    @Override
    public Vehicule rechercherVehicule(String type, String marque, String modele)
    {
        try
        {
            chargerVehicules();
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors du chargement des véhicules : " + e.getMessage());
        }

        for (Vehicule v : listeVehicules)
        {
            if (v.getType().equalsIgnoreCase(type) && v.getMarque().equalsIgnoreCase(marque) && v.getModele().equalsIgnoreCase(modele))
            {
                System.out.println(type + marque + modele);
                return v;
            }
        }
        return null;
    }
}
