import Contrôleur.Controleur;
import Modèle.GestionDeDonnees.Garage;
import Modèle.GestionDeDonnees.InterfaceGarage;
import Vue.InterfacesGraphiques.LoginWindow;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        Controleur controleur = new Controleur(new Garage(), new LoginWindow());
        controleur.run();
    }
}