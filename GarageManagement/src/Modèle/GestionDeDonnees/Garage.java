package Modèle.GestionDeDonnees;

import Modèle.ClassesMetier.Vehicule;
import Modèle.ClassesMetier.Voiture;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Garage implements InterfaceGarage
{
    private static final String FICHIER_VEHICULES = "Vehicles.txt";

    private  Garage instance;

    public  Garage getGarage()
    {
        if(instance == null)
        {
            instance = new Garage();
        }
        return instance;
    }
    @Override
    public void ajouterVehicule(Vehicule vehicule) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_VEHICULES, true)))
        {
            writer.write(formaterVehicule(vehicule));
            writer.newLine();
        }
    }

    @Override
    public void supprimerVehicule(String type, String marque, String modele) throws IOException
    {
        List<String> lignes = lireFichier();
        List<String> lignesModifiees = new ArrayList<>();

        for (String ligne : lignes) {
            String[] attributs = ligne.split(",");
            if (attributs.length > 2 && !(attributs[0].equals(type) && attributs[1].equals(marque) && attributs[2].equals(modele))) {
                lignesModifiees.add(ligne);
            }
        }

        ecrireFichier(lignesModifiees);
    }

    public void modifierVehicule(Vehicule vehicule) throws IOException
    {
        List<String> lignes = lireFichier();
        List<String> lignesModifiees = new ArrayList<>();

        for (String ligne : lignes)
        {
            String[] attributs = ligne.split(",");
            if (attributs.length > 2 && attributs[0].equals(vehicule.getType()) && attributs[1].equals(vehicule.getMarque()) && attributs[2].equals(vehicule.getModele()))
            {
                lignesModifiees.add(formaterVehicule(vehicule));
            } else {
                lignesModifiees.add(ligne);
            }
        }

        ecrireFichier(lignesModifiees);
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

    @Override
    public List<String> lireFichier() throws IOException
    {
        File fichier = new File(FICHIER_VEHICULES);
        if (!fichier.exists()) {
            fichier.createNewFile();
        }

        List<String> lignes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier)))
        {
            String ligne;
            while ((ligne = reader.readLine()) != null)
            {
                lignes.add(ligne);
            }
        }
        return lignes;
    }
    @Override
    public void ecrireFichier(List<String> lignes) throws IOException
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

    public void encoderVehicule()
    {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Entrez le type de véhicule (ex: Voiture): ");
            String type = scanner.nextLine();

            System.out.print("Entrez la marque: ");
            String marque = scanner.nextLine();

            System.out.print("Entrez le modèle: ");
            String modele = scanner.nextLine();

            System.out.print("Entrez la puissance: ");
            String puissance = scanner.nextLine();

            System.out.print("Entrez la transmission: ");
            String transmission = scanner.nextLine();

            System.out.print("Entrez le pays d'origine: ");
            String pays = scanner.nextLine();

            System.out.print("Entrez l'année: ");
            int annee = Integer.parseInt(scanner.nextLine());

            System.out.print("Entrez le chemin de l'image (ex: C:/Users/Loris/NissanGTR.jpg): ");
            String cheminImage = scanner.nextLine();

            Vehicule vehicule;
            if (type.equalsIgnoreCase("Voiture"))
            {
                vehicule = new Voiture(marque, modele, puissance, transmission, pays, annee, cheminImage);
            }
            else
            {
                System.out.println("Type non pris en charge pour le moment.");
                return;
            }

            ajouterVehicule(vehicule);
            System.out.println("Véhicule ajouté avec succès.");

        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de l'ajout du véhicule: " + e.getMessage());
        }
        catch (NumberFormatException e)
        {
            System.err.println("Année invalide.");
        }
    }

    public void afficherFichierVehicules() throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHIER_VEHICULES)))
        {
            String ligne;
            System.out.println("Contenu du fichier Vehicles.txt :");
            while ((ligne = reader.readLine()) != null) {
                System.out.println(ligne);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Le fichier Vehicles.txt n'existe pas encore.");
        }
    }

    public static void main(String[] args)
    {
        Garage garage = new Garage();

        try
        {
            Vehicule voiture = new Voiture("Nissan", "GTR", "600 ch", "Automatique", "Japon", 2021, "C:/Users/Loris/NissanGTR.jpg");

            // Ajout d'un véhicule
            //garage.ajouterVehicule(voiture);

            // Suppression d'un véhicule
            //garage.supprimerVehicule("Voiture", "Nissan", "GTR");

            // Modification d'un véhicule
            //Vehicule voitureModifiee = new Voiture("Nissan", "GTR", "650 ch", "Automatique", "Japon", 2022, "C:/Users/Loris/NissanGTR.jpg");
            //garage.modifierVehicule(voitureModifiee);

            //garage.encoderVehicule();

            garage.afficherFichierVehicules();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
