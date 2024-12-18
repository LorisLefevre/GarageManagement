package Controleur;

import Modele.GestionDeDonnees.InterfaceGarage;
import Vue.InterfacesGraphiques.GarageWindow;
import Vue.Vues.VueGarageWindow;
import Vue.Vues.VueLoginWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur implements ActionListener
{
    private final InterfaceGarage model;
    private final VueLoginWindow vueLoginWindow;

    private GarageWindow garageWindow;

    public Controleur(InterfaceGarage model, VueLoginWindow vueLoginWindow)
    {
        this.model = model;
        this.vueLoginWindow = vueLoginWindow;
        this.vueLoginWindow.setControleur(this);
    }

    public void setControleurGarage(VueGarageWindow vueGarageWindow)
    {
        this.garageWindow = (GarageWindow) vueGarageWindow;
        vueGarageWindow.setControleur(this);
    }

    public void run()
    {
        this.vueLoginWindow.run();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getActionCommand().equals(ActionsControleur.LOGIN))
        {
            vueLoginWindow.Login();
            VueGarageWindow vueGarageWindow = GarageWindow.getGarageWindow();
            setControleurGarage(vueGarageWindow);
        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.LOGOUT))
        {
            System.out.println("\nDéconnexion de l'utilisateur\n");
            vueLoginWindow.Logout();
        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.AJOUTER))
        {
            System.out.println("\nAjout d'un nouveau véhicule\n");
            garageWindow.Ajouter();

        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.SUPPRIMER))
        {
            System.out.println("\nSupression du véhicule\n");
            garageWindow.Supprimer();
        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.MODIFIER))
        {
            System.out.println("\nModification du véhicule\n");
            garageWindow.Modifier();
        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.AFFICHER))
        {
            System.out.println("\nVoir les données du fichier ou de la base de données\n");
            garageWindow.Afficher();
        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.VOIR))
        {
            System.out.println("\nVoir les informations du véhicule\n");
            garageWindow.Voir();
        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.TRIER))
        {
            System.out.println("\nTri de véhicules\n");
            garageWindow.Trier();
        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.CHANGER))
        {
            System.out.println("\nChangement de mode de travail\n");
            garageWindow.Changer();
        }
    }
}
