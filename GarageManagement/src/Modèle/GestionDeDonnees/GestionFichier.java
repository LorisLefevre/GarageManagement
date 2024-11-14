package Modèle.GestionDeDonnees;

import Modèle.ClassesMetier.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionFichier
{
    private static final String FICHIER_VEHICULES = "Vehicles.txt";

    private static GestionFichier instance;

    public static GestionFichier getInstance()
    {
        if(instance == null)
        {
            instance = new GestionFichier();
        }

        return instance;
    }

    public static void ecrireFichier(List<String> lignes) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_VEHICULES)))
        {
            for (String ligne : lignes) {
                writer.write(ligne);
                writer.newLine();
            }
        }
    }

    public static List<String> lireFichier() throws IOException
    {
        File fichier = new File(FICHIER_VEHICULES);
        if (!fichier.exists())
        {
            fichier.createNewFile();
        }

        List<String> lignes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier)))
        {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                lignes.add(ligne);
            }
        }
        return lignes;
    }

    public static List<Vehicule> chargerVehiculesDepuisFichier() throws IOException
    {
        List<Vehicule> listeVehicules = new ArrayList<>();
        List<String> lignes = lireFichier();

        for (String ligne : lignes) {
            String[] attributs = ligne.split(",");
            if (attributs.length == 8)
            {
                String type = attributs[0];
                String marque = attributs[1];
                String modele = attributs[2];
                String puissance = attributs[3];
                String transmission = attributs[4];
                String pays = attributs[5];
                int annee = Integer.parseInt(attributs[6]);
                String image = attributs[7];

                Vehicule vehicule;
                switch (type) {
                    case "Voiture":
                        vehicule = new Voiture(marque, modele, puissance, transmission, pays, annee, image);
                        break;
                    case "Moto":
                        vehicule = new Moto(marque, modele, puissance, transmission, pays, annee, image);
                        break;
                    case "Camionnette":
                        vehicule = new Camionnette(marque, modele, puissance, transmission, pays, annee, image);
                        break;
                    case "Camion":
                        vehicule = new Camion(marque, modele, puissance, transmission, pays, annee, image);
                        break;
                    default:
                        System.out.println("Type de véhicule inconnu: " + type);
                        continue; // Ignore cette ligne si le type est inconnu
                }

                listeVehicules.add(vehicule);
            }
        }
        return listeVehicules;
    }




}
