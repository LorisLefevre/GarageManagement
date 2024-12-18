package Vue.MethodesGarageWindow;

import Modele.ClassesMetier.Vehicule;
import Modele.GestionBaseDeDonnees.Requetes;
import Vue.InterfacesGraphiques.FormulaireVehicule;
import Vue.InterfacesGraphiques.GarageWindow;
import Vue.InterfacesGraphiques.VehiculeInformationWindow;

import javax.swing.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MethodesBoutonsGarageWindowBD
{
    private static final Logger logger = Logger.getLogger(MethodesBoutonsGarageWindowBD.class.getName());
    private static MethodesBoutonsGarageWindowBD instance;

    public static MethodesBoutonsGarageWindowBD getInstance()
    {
        if (instance == null)
        {
            instance = new MethodesBoutonsGarageWindowBD();
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


            Requetes.getInstance().AjouterVehicule(type,marque, modele,puissance, transmission, annee, pays, imagePath);

            MethodesGarageWindow.getInstance().RechargerTableBD("Tout");
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

        Requetes.getInstance().SupprimerVehicule(id);

        MethodesGarageWindow.getInstance().RechargerTableBD("Tout");
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

        Vehicule vehicule;
        try
        {
            vehicule = Requetes.getInstance().rechercherVehiculeParId(id);
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
                MethodesGarageWindow.getInstance().RechargerTableBD("Tout");

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

    public void BoutonVoirBD()
    {
        int selectedRow = GarageWindow.getGarageWindow().getTable().getSelectedRow();

        if (selectedRow == -1)
        {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un véhicule à voir.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 0).toString());

        try {
            // Recherche du véhicule par ID dans la base de données
            Vehicule vehicule = Requetes.getInstance().rechercherVehiculeParId(id);

            if (vehicule == null)
            {
                JOptionPane.showMessageDialog(null, "Véhicule introuvable dans la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String imagePath = vehicule.getImage();
            if (imagePath == null || imagePath.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Chemin de l'image introuvable ou invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            VehiculeInformationWindow vehiculeInformationWindow = VehiculeInformationWindow.getInstance();
            vehiculeInformationWindow.setVehiculeDetails(
                    vehicule.getMarque(),
                    vehicule.getModele(),
                    vehicule.getPuissance(),
                    vehicule.getTransmission(),
                    vehicule.getPays(),
                    vehicule.getAnnee(),
                    imagePath
            );
            vehiculeInformationWindow.run();

        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération du véhicule : " + e.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            logger.log(Level.SEVERE, "Les véhicules n'ont pas pu être récupérés");
        }
        catch (RuntimeException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            logger.log(Level.SEVERE, "Erreur de runtime");
        }
    }


    public void BoutonTrierBD()
    {
        String selectedType = (String) GarageWindow.getGarageWindow().getTrier().getSelectedItem();
        assert selectedType != null;
        MethodesGarageWindow.getInstance().RechargerTableBD(selectedType.equals("Tout") ? null : selectedType);
    }
}
