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
            //vueGarageWindow = GarageWindow.getGarageWindow();
        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.LOGOUT))
        {

        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.AJOUTER))
        {

        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.SUPPRIMER))
        {

        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.MODIFIER))
        {

        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.VOIR))
        {

        }

        if(actionEvent.getActionCommand().equals(ActionsControleur.TRIER))
        {

        }
    }
}
