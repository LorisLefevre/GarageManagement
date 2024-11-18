package Modèle.GestionDeDonnees;

import Modèle.ClassesMetier.Vehicule;

import java.io.IOException;
import java.util.List;

public interface InterfaceGarage
{
    void ajouterVehicule(Vehicule vehicule)throws IOException;
    void supprimerVehicule(int id) throws IOException;
    void modifierVehicule(int id, Vehicule vehicule) throws IOException;
    String formaterVehicule(Vehicule vehicule);
    public Vehicule rechercherVehiculeParId(int id);
}
