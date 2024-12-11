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
            for (String ligne : lignes)
            {
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

        for (String ligne : lignes)
        {
            String[] attributs = ligne.split(",");
            if (attributs.length == 9)
            {
                int id = Integer.parseInt(attributs[0]);
                String type = attributs[1];
                String marque = attributs[2];
                String modele = attributs[3];
                String puissance = attributs[4];
                String transmission = attributs[5];
                String pays = attributs[6];
                int annee = Integer.parseInt(attributs[7]);
                String image = attributs[8];

                Vehicule vehicule = VehiculeFactory.creerVehicule(id, type, marque, modele, puissance,
                        transmission, pays, annee, image);

                if (vehicule != null)
                {
                    listeVehicules.add(vehicule);
                }
            }
        }
        return listeVehicules;
    }

    public static int getDernierId() throws IOException
    {
        int dernierId = 0;

        List<String> lignes = lireFichier();
        for (String ligne : lignes)
        {
            String[] attributs = ligne.split(",");
            if (attributs.length > 0)
            {
                int id = Integer.parseInt(attributs[0]);
                dernierId = Math.max(dernierId, id);
            }
        }
        return dernierId;
    }
}
