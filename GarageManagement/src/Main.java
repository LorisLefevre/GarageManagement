import Controleur.Controleur;
import Modele.GestionDeDonnees.Garage;
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