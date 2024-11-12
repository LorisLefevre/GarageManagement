package Contrôleur;

import Modèle.GestionDeDonnees.InterfaceGarage;
import Vue.InterfacesGraphiques.GarageWindow;
import Vue.InterfacesGraphiques.LoginWindow;
import Vue.InterfacesGraphiques.VehiculeInformationWindow;
import Vue.Vues.VueGarageWindow;
import Vue.Vues.VueLoginWindow;
import Vue.Vues.VueVehicleInformationWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controleur implements ActionListener
{
    private InterfaceGarage model;
    private VueLoginWindow vueLoginWindow;
    private LoginWindow loginWindow;

    private VueGarageWindow vueGarageWindow;
    private GarageWindow garageWindow;

    private VueVehicleInformationWindow vueVehicleInformationWindow;
    private VehiculeInformationWindow vehiculeInformationWindow;

    public Controleur(InterfaceGarage model, VueLoginWindow vueLoginWindow)
    {
        this.model = model;
        this.vueLoginWindow = vueLoginWindow;
        this.vueLoginWindow.setContrôleur(this);
    }

    public void setControleurLogin(VueLoginWindow vueLoginWindow)
    {
        this.loginWindow = (LoginWindow) vueLoginWindow;
        vueLoginWindow.setContrôleur(this);
    }

    public void setControleurGarage(VueGarageWindow vueGarageWindow)
    {
        this.garageWindow = (GarageWindow) vueGarageWindow;
        vueGarageWindow.setContrôleur(this);
    }

    public void setControleurVehiculeInformation(VueVehicleInformationWindow vueVehicleInformationWindow)
    {
        this.vehiculeInformationWindow = (VehiculeInformationWindow) vueVehicleInformationWindow;
        vueVehicleInformationWindow.setContrôleur(this);
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
            vueGarageWindow = GarageWindow.getGarageWindow();
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

        if(actionEvent.getActionCommand().equals(ActionsControleur.VOIR))
        {
            System.out.println("\nVoir les informations du véhicule\n");
        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.TRIER))
        {
            System.out.println("\nTri de véhicules\n");
            garageWindow.Trier();
        }
    }
}
