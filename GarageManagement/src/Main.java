import Contrôleur.Controleur;
import Modèle.GestionDeDonnees.Garage;
import Vue.InterfacesGraphiques.LoginWindow;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Bienvenue dans GarageManagement");
        Controleur controleur = new Controleur(new Garage(), new LoginWindow());
        controleur.run();
    }
}