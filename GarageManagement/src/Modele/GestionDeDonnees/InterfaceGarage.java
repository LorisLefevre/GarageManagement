package Modele.GestionDeDonnees;

import Modele.ClassesMetier.Vehicule;

import java.io.IOException;

public interface InterfaceGarage
{
    void ajouterVehicule(Vehicule vehicule)throws IOException;
    void supprimerVehicule(int id) throws IOException;
    void modifierVehicule(int id, Vehicule vehicule) throws IOException;
    String formaterVehicule(Vehicule vehicule);
    Vehicule rechercherVehiculeParId(int id);
}
