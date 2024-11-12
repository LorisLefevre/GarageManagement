package Modèle.GestionDeDonnees;
import Modèle.ClassesMetier.*;
import Modèle.GestionDeDonnees.InterfaceGarage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Garage implements InterfaceGarage
{
    private static final String FICHIER_VEHICULES = "Vehicles.txt";
    private static Garage instance;
    private List<Vehicule> listeVehicules;  // Déclaration de la liste de véhicules

    // Constructeur privé pour le singleton
    public Garage() {
        listeVehicules = new ArrayList<>();  // Initialisation de la liste des véhicules
    }

    // Méthode pour obtenir l'instance unique du garage
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
        listeVehicules.add(vehicule);  // Ajout du véhicule à la liste
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_VEHICULES, true)))
        {
            writer.write(formaterVehicule(vehicule));
            writer.newLine();
        }
    }

    @Override
    public void supprimerVehicule(String type, String marque, String modele) throws IOException {
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

    public void modifierVehicule(String ancienType, String ancienneMarque, String ancienModele, Vehicule vehiculeModifie) throws IOException
    {
        List<String> lignes = lireFichier();
        List<String> lignesModifiees = new ArrayList<>();

        for (String ligne : lignes)
        {
            String[] attributs = ligne.split(",");

            // On cherche le véhicule avec les anciens attributs
            if (attributs.length > 2 &&
                    attributs[0].equalsIgnoreCase(ancienType) &&
                    attributs[1].equalsIgnoreCase(ancienneMarque) &&
                    attributs[2].equalsIgnoreCase(ancienModele)) {
                // Si trouvé, on ajoute le véhicule modifié
                lignesModifiees.add(formaterVehicule(vehiculeModifie));
            }
            else
            {
                // Sinon, on conserve la ligne telle quelle
                lignesModifiees.add(ligne);
            }
        }

        ecrireFichier(lignesModifiees);
    }


    @Override
    public String formaterVehicule(Vehicule vehicule) {
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
    public List<String> lireFichier() throws IOException {
        File fichier = new File(FICHIER_VEHICULES);
        if (!fichier.exists()) {
            fichier.createNewFile();
        }

        List<String> lignes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                lignes.add(ligne);
            }
        }
        return lignes;
    }

    @Override
    public void ecrireFichier(List<String> lignes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_VEHICULES))) {
            for (String ligne : lignes) {
                writer.write(ligne);
                writer.newLine();
            }
        }
    }

    public void chargerVehiculesDepuisFichier() throws IOException {
        listeVehicules.clear();
        List<String> lignes = lireFichier();

        for (String ligne : lignes) {
            String[] attributs = ligne.split(",");
            if (attributs.length == 8) {  // Vérifie qu'il y a bien 8 attributs
                String type = attributs[0]; // Type du véhicule (Voiture, Camion, etc.)
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
    }


    public Vehicule rechercherVehicule(String type, String marque, String modele)
    {
        try
        {
            chargerVehiculesDepuisFichier();
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


    public void encoderVehicule() {
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
            } else {
                System.out.println("Type non pris en charge pour le moment.");
                return;
            }

            ajouterVehicule(vehicule);
            System.out.println("Véhicule ajouté avec succès.");

        } catch (IOException e) {
            System.err.println("Erreur lors de l'ajout du véhicule: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Année invalide.");
        }
    }

    public void afficherFichierVehicules() throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHIER_VEHICULES)))
        {
            String ligne;
            System.out.println("Contenu du fichier Vehicles.txt :");
            while ((ligne = reader.readLine()) != null)
            {
                System.out.println(ligne);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Le fichier Vehicles.txt n'existe pas encore.");
        }
    }

    public static void main(String[] args) {
        Garage garage = Garage.getGarage();  // Utiliser le singleton

        try {
            //Vehicule voiture = new Voiture("Nissan", "GTR", "65 ch", "Manuelle", "Japon", 2005, "");
            //garage.ajouterVehicule(voiture);

            //voiture.setPuissance("650 ch");
            //voiture.setTransmission("Automatique");
            //voiture.setAnnee(2022);

            //garage.modifierVehicule(voiture);
            garage.afficherFichierVehicules();

            Vehicule vehicule = garage.rechercherVehicule("Voiture", "Renault", "Clio");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
