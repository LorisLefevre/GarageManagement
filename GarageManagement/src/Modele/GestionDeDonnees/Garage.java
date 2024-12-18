package Modele.GestionDeDonnees;
import Modele.ClassesMetier.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


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
        if (instance == null)
        {
            instance = new Garage();
        }
        return instance;
    }

    @Override
    public void ajouterVehicule(Vehicule vehicule) throws IOException
    {
        int dernierId = GestionFichier.getDernierId();
        int nouvelId = dernierId + 1;

        vehicule.setIdentifiant(nouvelId);

        listeVehicules.add(vehicule);
        List<String> lignes = GestionFichier.lireFichier();
        lignes.add(formaterVehicule(vehicule));
        GestionFichier.ecrireFichier(lignes);
    }

    @Override
    public void supprimerVehicule(int id) throws IOException
    {
        List<String> lignes = GestionFichier.lireFichier();
        List<String> lignesModifiees = new ArrayList<>();

        for (String ligne : lignes) {
            String[] attributs = ligne.split(",");
            if (attributs.length > 0 && Integer.parseInt(attributs[0]) != id) {
                lignesModifiees.add(ligne);
            }
        }

        GestionFichier.ecrireFichier(lignesModifiees);
        chargerVehicules();
    }

    @Override
    public void modifierVehicule(int id, Vehicule vehiculeModifie) throws IOException
    {
        List<String> lignes = GestionFichier.lireFichier();
        List<String> lignesModifiees = new ArrayList<>();

        for (String ligne : lignes) {
            String[] attributs = ligne.split(",");

            if (attributs.length > 0 && Integer.parseInt(attributs[0]) == id)
            {
                lignesModifiees.add(formaterVehicule(vehiculeModifie));
            } else {
                lignesModifiees.add(ligne);
            }
        }

        GestionFichier.ecrireFichier(lignesModifiees);
        chargerVehicules();
    }


    @Override
    public String formaterVehicule(Vehicule vehicule)
    {
        return  vehicule.getIdentifiant() + "," +
                vehicule.getType() + "," +
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
    public Vehicule rechercherVehiculeParId(int id)
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
            if (v.getIdentifiant() == id)
            {
                return v;
            }
        }
        return null;
    }

}
