package Vue.InterfacesGraphiques;

import Modele.ClassesMetier.*;

import javax.swing.*;
import java.io.File;

public class FormulaireVehicule
{
    private static FormulaireVehicule instance;

    public static FormulaireVehicule getInstance()
    {
        if(instance == null)
        {
            instance = new FormulaireVehicule();
        }
        return instance;
    }

    public JPanel VehiculeFormulaire(Vehicule vehicule)
    {
        String[] types = {"Voiture", "Moto", "Camionnette", "Camion"};
        JComboBox<String> typeComboBox = new JComboBox<>(types);
        typeComboBox.setSelectedItem(vehicule != null ? vehicule.getType() : types[0]);

        JTextField marqueField = new JTextField(vehicule != null ? vehicule.getMarque() : "", 10);
        JTextField modeleField = new JTextField(vehicule != null ? vehicule.getModele() : "", 10);
        JTextField puissanceField = new JTextField(vehicule != null ? vehicule.getPuissance() : "", 10);
        JTextField transmissionField = new JTextField(vehicule != null ? vehicule.getTransmission() : "", 10);
        JTextField anneeField = new JTextField(vehicule != null ? String.valueOf(vehicule.getAnnee()) : "", 4);
        JTextField paysField = new JTextField(vehicule != null ? vehicule.getPays() : "", 10);
        JTextField imagePathField = new JTextField(vehicule != null ? vehicule.getImage() : "", 20);
        JButton imageButton = new JButton("Choisir Image");

        imageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imagePathField.setText(selectedFile.getAbsolutePath());
            }
        });

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
        panel.add(imageButton);

        return panel;
    }

    public Vehicule CreerVehiculeDeFormulaire(String type, String marque, String modele, String puissance,
                                              String transmission, int annee, String pays, String imagePath)
    {
        return VehiculeFactory.creerVehicule(type, marque, modele, puissance, transmission, pays,annee, imagePath);
    }
}
