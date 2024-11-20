package Vue.MéthodesGarageWindow;

import Modèle.ClassesMetier.Vehicule;
import Modèle.GestionBaseDeDonnees.Requetes;
import Modèle.GestionDeDonnees.Garage;
import Vue.InterfacesGraphiques.FormulaireVehicule;
import Vue.InterfacesGraphiques.GarageWindow;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;


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
            String type = (String) ((JComboBox<?>) panel.getComponent(1)).getSelectedItem();
            String marque = ((JTextField) panel.getComponent(3)).getText();
            String modele = ((JTextField) panel.getComponent(5)).getText();
            String puissance = ((JTextField) panel.getComponent(7)).getText();
            String transmission = ((JTextField) panel.getComponent(9)).getText();
            int annee;
            try
            {
                annee = Integer.parseInt(((JTextField) panel.getComponent(11)).getText());
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Veuillez entrer une année valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String pays = ((JTextField) panel.getComponent(13)).getText();
            String imagePath = ((JTextField) panel.getComponent(15)).getText();

            if (marque.isEmpty() || modele.isEmpty() || puissance.isEmpty() || transmission.isEmpty() || pays.isEmpty() || imagePath.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Vehicule vehicule = FormulaireVehicule.getInstance().CreerVehiculeDeFormulaire(type, marque, modele, puissance, transmission, annee, pays, imagePath);

            Requetes.getInstance().AjouterVehicule(type,marque, modele,puissance, transmission, annee, pays, imagePath);

            MéthodesGarageWindow.getInstance().RechargerTableBD();
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

        int id = Integer.parseInt(GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 0).toString());
        String type = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 1);
        String marque = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 2);
        String modele = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 3);
        String puissance = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 4);
        String transmission = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 5);
        String pays = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 6);
        int annee = Integer.parseInt((String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 7).toString());
        String imagePath = "";

        Vehicule vehiculeSupprime = FormulaireVehicule.getInstance().CreerVehiculeDeFormulaire(type, marque, modele, puissance, transmission, annee, pays, imagePath);

        Requetes.getInstance().SupprimerVehicule(id, type);

        MéthodesGarageWindow.getInstance().RechargerTableBD();
    }

    public void BoutonModifierBD()
    {
        int selectedRow = GarageWindow.getGarageWindow().getTable().getSelectedRow();

        if (selectedRow == -1)
        {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un véhicule à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 0).toString());
        String type = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 1);

        Vehicule vehicule;
        try
        {
            vehicule = Requetes.getInstance().rechercherVehiculeParId(type, id);
            if (vehicule == null)
            {
                JOptionPane.showMessageDialog(null, "Véhicule non trouvé dans la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erreur lors de la recherche du véhicule : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel panel = FormulaireVehicule.getInstance().VehiculeFormulaire(vehicule);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Modifier un véhicule",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION)
        {
            try
            {
                vehicule.setType((String) ((JComboBox<?>) panel.getComponent(1)).getSelectedItem());
                vehicule.setMarque(((JTextField) panel.getComponent(3)).getText());
                vehicule.setModele(((JTextField) panel.getComponent(5)).getText());
                vehicule.setPuissance(((JTextField) panel.getComponent(7)).getText());
                vehicule.setTransmission(((JTextField) panel.getComponent(9)).getText());
                vehicule.setAnnee(Integer.parseInt(((JTextField) panel.getComponent(11)).getText()));
                vehicule.setPays(((JTextField) panel.getComponent(13)).getText());
                vehicule.setImage(((JTextField) panel.getComponent(15)).getText());

                Requetes.getInstance().modifierVehicule(id, vehicule);

                JOptionPane.showMessageDialog(null, "Véhicule modifié avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                MéthodesGarageWindow.getInstance().RechargerTableBD();

            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs valides dans le formulaire.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Erreur lors de la modification du véhicule : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
