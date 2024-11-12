package Modèle.GestionDeDonnees;

import Modèle.ClassesMetier.Vehicule;

import java.io.IOException;
import java.util.List;

public interface InterfaceGarage
{
    void ajouterVehicule(Vehicule vehicule)throws IOException;
    void supprimerVehicule(String type, String marque, String modele) throws IOException;
    void modifierVehicule(String ancienType, String ancienneMarque, String ancienModele, Vehicule vehicule) throws IOException;
    String formaterVehicule(Vehicule vehicule);
    List<String> lireFichier() throws IOException;
    void ecrireFichier(List<String> lignes) throws IOException;
}
