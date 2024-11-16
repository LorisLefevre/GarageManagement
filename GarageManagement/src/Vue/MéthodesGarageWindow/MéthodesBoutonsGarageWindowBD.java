package Vue.MéthodesGarageWindow;

import Modèle.ClassesMetier.Vehicule;
import Modèle.GestionBaseDeDonnees.Requetes;
import Modèle.GestionDeDonnees.Garage;
import Vue.InterfacesGraphiques.FormulaireVehicule;
import Vue.InterfacesGraphiques.GarageWindow;

import javax.swing.*;
import java.io.IOException;

public class MéthodesBoutonsGarageWindowBD
{
    private static MéthodesBoutonsGarageWindowBD instance;

    public static MéthodesBoutonsGarageWindowBD getInstance()
    {
        if (instance == null)
        {
            instance = new MéthodesBoutonsGarageWindowBD();
        }

        return instance;
    }

    public void BoutonAjouterBD()
    {
        JPanel panel = FormulaireVehicule.getInstance().VehiculeFormulaire(null);

        int result = JOptionPane.showConfirmDialog(null, panel, "Ajouter un véhicule", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if(result == JOptionPane.OK_OPTION)
        {
            String type = (String) ((JComboBox<?>) panel.getComponent(1)).getSelectedItem(); // Index 1 pour le JComboBox
            String marque = ((JTextField) panel.getComponent(3)).getText(); // Index 3 pour le JTextField "Marque"
            String modele = ((JTextField) panel.getComponent(5)).getText(); // Index 5 pour le JTextField "Modèle"
            String puissance = ((JTextField) panel.getComponent(7)).getText(); // Index 7 pour "Puissance"
            String transmission = ((JTextField) panel.getComponent(9)).getText(); // Index 9 pour "Transmission"
            int annee;
            try
            {
                annee = Integer.parseInt(((JTextField) panel.getComponent(11)).getText()); // Index 11 pour "Année"
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Veuillez entrer une année valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String pays = ((JTextField) panel.getComponent(13)).getText(); // Index 13 pour "Pays"
            String imagePath = ((JTextField) panel.getComponent(15)).getText(); // Index 15 pour "Image"

            if (marque.isEmpty() || modele.isEmpty() || puissance.isEmpty() || transmission.isEmpty() || pays.isEmpty() || imagePath.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Vehicule vehicule = FormulaireVehicule.getInstance().CreerVehiculeDeFormulaire(type, marque, modele, puissance, transmission, annee, pays, imagePath);

            Requetes.getInstance().AjouterVehicule(type,marque, modele,puissance, transmission, annee, pays, imagePath);

            MéthodesGarageWindow.getInstance().RechargerTable();
        }
    }

    public void BoutonSupprimerBD()
    {
        int selectedRow = GarageWindow.getGarageWindow().getTable().getSelectedRow();

        if (selectedRow == -1)
        {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un véhicule à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String type = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 0);
        String marque = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 1);
        String modele = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 2);
        String puissance = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 3);
        String transmission = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 4);
        String pays = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 5);
        int annee = Integer.parseInt((String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 6));
        String imagePath = "";

        Vehicule vehiculeSupprime = FormulaireVehicule.getInstance().CreerVehiculeDeFormulaire(type, marque, modele, puissance, transmission, annee, pays, imagePath);

        Requetes.getInstance().SupprimerVehicule(type,marque, modele);

        MéthodesGarageWindow.getInstance().RechargerTable();
    }

    public void BoutonModifierBD()
    {

    }
}
