package Vue.MéthodesBoutonsGarage;

import Modèle.ClassesMetier.*;
import Modèle.GestionDeDonnees.Garage;

import javax.swing.*;
import java.io.File;

public class MéthodeBoutonGarageWindow
{
    private static MéthodeBoutonGarageWindow instance;

    public static MéthodeBoutonGarageWindow getInstance()
    {
        if (instance == null)
        {
            instance = new MéthodeBoutonGarageWindow();
        }

        return instance;
    }

    public void BoutonAjouter()
    {
        JOptionPane.showMessageDialog(null, "Ajout de véhicule");

        // Création des champs du formulaire
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
}
