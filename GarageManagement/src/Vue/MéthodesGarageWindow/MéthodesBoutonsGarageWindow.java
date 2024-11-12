package Vue.MéthodesGarageWindow;

import Modèle.ClassesMetier.*;
import Modèle.GestionDeDonnees.Garage;
import Vue.InterfacesGraphiques.GarageWindow;

import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class MéthodesBoutonsGarageWindow
{
    private GarageWindow garageWindow;
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
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Voiture", "Moto", "Camionnette", "Camion"});
        JTextField marqueField = new JTextField(10);
        JTextField modeleField = new JTextField(10);
        JTextField puissanceField = new JTextField(10);
        JTextField transmissionField = new JTextField(10);
        JTextField anneeField = new JTextField(4);
        JTextField paysField = new JTextField(10);
        JTextField imagePathField = new JTextField(20);
        JButton imageButton = new JButton("Choisir Image");

        // Action pour le bouton de sélection d'image
        imageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imagePathField.setText(selectedFile.getAbsolutePath());
            }
        });

        // Ajout des champs à un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Type de véhicule:"));
        panel.add(typeComboBox);
        panel.add(new JLabel("Marque:"));
        panel.add(marqueField);
        panel.add(new JLabel("Modèle:"));
        panel.add(modeleField);
        panel.add(new JLabel("Puissance:"));
        panel.add(puissanceField);
        panel.add(new JLabel("Transmission:"));
        panel.add(transmissionField);
        panel.add(new JLabel("Année:"));
        panel.add(anneeField);
        panel.add(new JLabel("Pays:"));
        panel.add(paysField);
        panel.add(new JLabel("Image:"));
        panel.add(imagePathField);
        panel.add(imageButton);

        // Afficher le dialogue
        int result = JOptionPane.showConfirmDialog(null, panel, "Ajouter un véhicule", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION)
        {
            String type = (String) typeComboBox.getSelectedItem();
            String marque = marqueField.getText();
            String modele = modeleField.getText();
            String puissance = puissanceField.getText();
            String transmission = transmissionField.getText();
            int annee;
            try {
                annee = Integer.parseInt(anneeField.getText());
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Veuillez entrer une année valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String pays = paysField.getText();
            String imagePath = imagePathField.getText();

            // Vérifier que les champs requis sont remplis
            if (marque.isEmpty() || modele.isEmpty() || puissance.isEmpty() || transmission.isEmpty() || pays.isEmpty() || imagePath.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Vehicule vehicule;
            switch (type)
            {
                case "Voiture":
                    vehicule = new Voiture(marque, modele, puissance, transmission, pays, annee, imagePath);
                    break;
                case "Moto":
                    vehicule = new Moto(marque, modele, puissance, transmission, pays, annee, imagePath);
                    break;
                case "Camionnette":
                    vehicule = new Camionnette(marque, modele, puissance, transmission, pays, annee, imagePath);
                    break;
                case "Camion":
                    vehicule = new Camion(marque, modele, puissance, transmission, pays, annee, imagePath);
                    break;
                default:
                    throw new IllegalArgumentException("Type de véhicule non supporté: " + type);
            }

            try
            {
                Garage.getGarage().ajouterVehicule(vehicule);
                JOptionPane.showMessageDialog(null, "Véhicule ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du véhicule: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void BoutonSupprimer()
    {
        int selectedRow = GarageWindow.getGarageWindow().getTable().getSelectedRow();

        // Vérifier si une ligne est sélectionnée
        if (selectedRow == -1)
        {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un véhicule à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Récupérer les valeurs actuelles du véhicule sélectionné
        String type = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 0);
        String marque = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 1);
        String modele = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 2);
        String puissance = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 3);
        String transmission = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 4);
        String pays = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 5);
        int annee = Integer.parseInt((String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 6));
        String imagePath = "";

        Vehicule vehiculeSupprime;
        switch (type)
        {
            case "Voiture":
                vehiculeSupprime = new Voiture(marque, modele, puissance, transmission, pays, annee, imagePath);
                break;
            case "Moto":
                vehiculeSupprime = new Moto(marque, modele, puissance, transmission, pays, annee, imagePath);
                break;
            case "Camionnette":
                vehiculeSupprime = new Camionnette(marque, modele, puissance, transmission, pays, annee, imagePath);
                break;
            case "Camion":
                vehiculeSupprime = new Camion(marque, modele, puissance, transmission, pays, annee, imagePath);
                break;
            default:
                throw new IllegalArgumentException("Type de véhicule non supporté: " + type);
        }

        try
        {
            Garage.getGarage().supprimerVehicule(type, marque, modele);
            JOptionPane.showMessageDialog(null, "Véhicule supprimmé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du véhicule: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        MéthodesGarageWindow.getInstance().loadTableData("Tout");
    }

    public void BoutonModifier() {
        int selectedRow = GarageWindow.getGarageWindow().getTable().getSelectedRow();

        // Vérifier si une ligne est sélectionnée
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un véhicule à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Récupérer les valeurs actuelles du véhicule sélectionné
        String type = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 0);
        String marque = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 1);
        String modele = (String) GarageWindow.getGarageWindow().getTable().getValueAt(selectedRow, 2);

        Vehicule vehicule = Garage.getGarage().rechercherVehicule(type, marque, modele);
        if (vehicule == null) {
            JOptionPane.showMessageDialog(null, "Véhicule non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ComboBox pour modifier le type
        String[] types = {"Voiture", "Moto", "Camionnette", "Camion"};
        JComboBox<String> typeComboBox = new JComboBox<>(types);
        typeComboBox.setSelectedItem(type);

        JTextField marqueField = new JTextField(vehicule.getMarque(), 10);
        JTextField modeleField = new JTextField(vehicule.getModele(), 10);
        JTextField puissanceField = new JTextField(vehicule.getPuissance(), 10);
        JTextField transmissionField = new JTextField(vehicule.getTransmission(), 10);
        JTextField anneeField = new JTextField(String.valueOf(vehicule.getAnnee()), 4);
        JTextField paysField = new JTextField(vehicule.getPays(), 10);
        JTextField imagePathField = new JTextField(vehicule.getImage(), 20);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Type :"));
        panel.add(typeComboBox);
        panel.add(new JLabel("Marque :"));
        panel.add(marqueField);
        panel.add(new JLabel("Modèle :"));
        panel.add(modeleField);
        panel.add(new JLabel("Puissance :"));
        panel.add(puissanceField);
        panel.add(new JLabel("Transmission :"));
        panel.add(transmissionField);
        panel.add(new JLabel("Année :"));
        panel.add(anneeField);
        panel.add(new JLabel("Pays :"));
        panel.add(paysField);
        panel.add(new JLabel("Image :"));
        panel.add(imagePathField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Modifier un véhicule", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            vehicule.setType((String) typeComboBox.getSelectedItem());
            vehicule.setMarque(marqueField.getText());
            vehicule.setModele(modeleField.getText());
            vehicule.setPuissance(puissanceField.getText());
            vehicule.setTransmission(transmissionField.getText());

            try {
                vehicule.setAnnee(Integer.parseInt(anneeField.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer une année valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            vehicule.setPays(paysField.getText());
            vehicule.setImage(imagePathField.getText());

            try {
                Garage.getGarage().modifierVehicule(type, marque, modele, vehicule);
                JOptionPane.showMessageDialog(null, "Véhicule modifié avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                MéthodesGarageWindow.getInstance().loadTableData("Tout");
            } catch (IOException ex) {
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
