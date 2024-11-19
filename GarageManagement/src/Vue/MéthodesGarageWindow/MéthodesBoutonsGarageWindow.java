package Vue.MéthodesGarageWindow;

import Modèle.ClassesMetier.*;
import Modèle.GestionBaseDeDonnees.Requetes;
import Modèle.GestionDeDonnees.Garage;
import Vue.InterfacesGraphiques.FormulaireVehicule;
import Vue.InterfacesGraphiques.GarageWindow;
import javax.swing.*;
import java.io.IOException;


public class MéthodesBoutonsGarageWindow
{
    private static MéthodesBoutonsGarageWindow instance;

    public static MéthodesBoutonsGarageWindow getInstance()
    {
        if (instance == null)
        {
            instance = new MéthodesBoutonsGarageWindow();
        }

        return instance;
    }

    public void BoutonAjouter()
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

            try
            {
                Garage.getGarage().ajouterVehicule(vehicule);
                JOptionPane.showMessageDialog(null, "Véhicule ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du véhicule: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            MéthodesGarageWindow.getInstance().RechargerTable();
        }
    }

    public void BoutonSupprimer()
    {
        int selectedRow = GarageWindow.getGarageWindow().getTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un véhicule à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 0).toString());

        int confirmation = JOptionPane.showConfirmDialog(
                null,
                "Êtes-vous sûr de vouloir supprimer ce véhicule ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmation == JOptionPane.YES_OPTION)
        {
            try
            {
                Garage.getGarage().supprimerVehicule(id);
                JOptionPane.showMessageDialog(null, "Véhicule supprimé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                MéthodesGarageWindow.getInstance().RechargerTable();
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du véhicule: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void BoutonModifier()
    {
        int selectedRow = GarageWindow.getGarageWindow().getTable().getSelectedRow();

        if (selectedRow == -1)
        {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un véhicule à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 0).toString());

        Vehicule vehicule = Garage.getGarage().rechercherVehiculeParId(id);
        if (vehicule == null)
        {
            JOptionPane.showMessageDialog(null, "Véhicule non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
            vehicule.setType((String) ((JComboBox<?>) panel.getComponent(1)).getSelectedItem());
            vehicule.setMarque(((JTextField) panel.getComponent(3)).getText());
            vehicule.setModele(((JTextField) panel.getComponent(5)).getText());
            vehicule.setPuissance(((JTextField) panel.getComponent(7)).getText());
            vehicule.setTransmission(((JTextField) panel.getComponent(9)).getText());

            try
            {
                vehicule.setAnnee(Integer.parseInt(((JTextField) panel.getComponent(11)).getText()));
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Veuillez entrer une année valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            vehicule.setPays(((JTextField) panel.getComponent(13)).getText());
            vehicule.setImage(((JTextField) panel.getComponent(15)).getText());

            try
            {
                Garage.getGarage().modifierVehicule(id, vehicule);
                JOptionPane.showMessageDialog(null, "Véhicule modifié avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                MéthodesGarageWindow.getInstance().RechargerTable();
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(null, "Erreur lors de la modification du véhicule : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void BoutonTrier()
    {
        String selectedType = (String) GarageWindow.getGarageWindow().getTrier().getSelectedItem();
        MéthodesGarageWindow.getInstance().filterTableByType(selectedType);
    }
}
