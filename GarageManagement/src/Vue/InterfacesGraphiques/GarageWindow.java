package Vue.InterfacesGraphiques;

import Contrôleur.ActionsControleur;
import Contrôleur.Controleur;
import Modèle.ClassesMetier.*;
import Modèle.GestionDeDonnees.Garage;
import Vue.Vues.VueGarageWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GarageWindow extends JFrame implements VueGarageWindow
{
    private DefaultTableModel model;
    private List<Object[]> data; // Pour stocker les données initiales

    private JLabel userLabel;

    public JLabel getUserLabel()
    {
        return  userLabel;
    }

    public void setUserLabel(String user)
    {
        userLabel.setText("Utilisateur : " + user);
    }

    private static final String FICHIER_VEHICULES = "Vehicles.txt"; // Chemin vers le fichier

    private Garage garage;
    private static GarageWindow instance;
    public static GarageWindow getGarageWindow()
    {
        if(instance == null)
        {
            instance = new GarageWindow();
        }
        return instance;
    }

    private JButton Ajouter;
    private JButton Modifier;
    private JButton Supprimer;
    private JButton Voir;


    public GarageWindow()
    {
        setTitle("Garage Window");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialiser la liste de données
        data = new ArrayList<>();
        lireDonneesDepuisFichier(); // Charger les données depuis le fichier

        // Création du panneau supérieur pour les boutons et l'utilisateur
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Étiquette utilisateur
        userLabel = new JLabel();
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(userLabel);

        Ajouter = new JButton("Ajouter");
        Modifier = new JButton("Modifier");
        Supprimer = new JButton("Supprimer");
        Voir = new JButton("Voir");

        topPanel.add(Ajouter);
        topPanel.add(Modifier);
        topPanel.add(Supprimer);
        topPanel.add(Voir);

        // Ajout du comboBox pour le tri par type de véhicule
        JComboBox<String> sortComboBox = new JComboBox<>(new String[]{"Tout", "Voiture", "Moto", "Camionnette", "Camion"});
        sortComboBox.addActionListener(e -> {
            String selectedType = (String) sortComboBox.getSelectedItem();
            filterTableByType(selectedType);
        });
        topPanel.add(new JLabel(" Trier par :"));
        topPanel.add(sortComboBox);

        add(topPanel, BorderLayout.NORTH);

        // Définition des colonnes
        String[] columnNames = {"Type", "Marque", "Modèle", "Puissance", "Transmission", "Pays", "Année", "Image"};
        model = new DefaultTableModel(columnNames, 0);

        // Création de la JTable avec le modèle
        JTable table = new JTable(model);
        loadTableData("Tout"); // Charger toutes les données initiales

        // Personnaliser le rendu pour afficher les images dans la JTable
        table.getColumn("Image").setCellRenderer(new ImageRenderer());

        // Ajouter la table dans un JScrollPane pour activer le défilement
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panneau inférieur pour les messages de succès ou d’erreurs
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel messageLabel = new JLabel("Données de véhicules affichées avec succès");
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(messageLabel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    private void lireDonneesDepuisFichier()
    {
        try
        {
            List<String> lignes = Garage.getGarage().lireFichier();

            // Parcourir chaque ligne et extraire les données
            for (String ligne : lignes)
            {
                String[] elements = ligne.split(",");
                if (elements.length >= 8) {
                    String type = elements[0];
                    String marque = elements[1];
                    String modele = elements[2];
                    String puissance = elements[3];
                    String transmission = elements[4];
                    String pays = elements[5];
                    String annee = elements[6];
                    String cheminImage = elements[7];
                    data.add(new Object[]{type, marque, modele, puissance, transmission, pays, annee, getScaledImage(cheminImage)});
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    // La méthode lireFichier que vous avez définie
    public List<String> lireFichier() throws IOException
    {
        File fichier = new File(FICHIER_VEHICULES);
        if (!fichier.exists()) {
            fichier.createNewFile();
        }

        List<String> lignes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                lignes.add(ligne);
            }
        }
        return lignes;
    }


    // Méthode pour charger les données dans la table selon le type sélectionné
    private void loadTableData(String type) {
        model.setRowCount(0); // Vider le modèle de table

        for (Object[] row : data) {
            if (type.equals("Tout") || row[0].equals(type)) {
                model.addRow(row);
            }
        }
    }

    // Méthode pour appliquer le filtre de type de véhicule
    private void filterTableByType(String type) {
        loadTableData(type);
    }

    // Méthode pour redimensionner et charger l'image
    private ImageIcon getScaledImage(String path)
    {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            Image scaledImage = img.getScaledInstance(200, 60, Image.SCALE_SMOOTH); // Redimensionner l'image
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Classe pour rendre les cellules de la colonne Image avec des icônes
    static class ImageRenderer extends JLabel implements TableCellRenderer {
        public ImageRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
            } else {
                setText(value != null ? value.toString() : "");
            }
            return this;
        }
    }

    public void  addAjoutListener(ActionListener actionListener)
    {
        Ajouter.addActionListener(actionListener);
    }

    public void  addModifierListener(ActionListener actionListener)
    {
        Modifier.addActionListener(actionListener);
    }

    public void  addSupprimerListener(ActionListener actionListener)
    {
        Supprimer.addActionListener(actionListener);
    }

    public void  addVoirListener(ActionListener actionListener)
    {
        Voir.addActionListener(actionListener);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }
    @Override
    public void run()
    {
        this.setVisible(true);
    }

    @Override
    public void setContrôleur(Controleur controleur)
    {
        Ajouter.setActionCommand(ActionsControleur.AJOUTER);
        Modifier.setActionCommand(ActionsControleur.MODIFIER);
        Supprimer.setActionCommand(ActionsControleur.SUPPRIMER);
        Voir.setActionCommand(ActionsControleur.VOIR);

        Ajouter.addActionListener(controleur);
        Modifier.addActionListener(controleur);
        Supprimer.addActionListener(controleur);
        Voir.addActionListener(controleur);
    }

    public void Ajouter() {
        showMessage("Ajout de véhicule");

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

        // Traitement de la réponse
        if (result == JOptionPane.OK_OPTION) {
            String type = (String) typeComboBox.getSelectedItem();
            String marque = marqueField.getText();
            String modele = modeleField.getText();
            String puissance = puissanceField.getText();
            String transmission = transmissionField.getText();
            int annee;
            try {
                annee = Integer.parseInt(anneeField.getText());
            } catch (NumberFormatException e) {
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

            // Créer et ajouter le véhicule en fonction du type sélectionné
            Vehicule vehicule;
            switch (type) {
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

            try {
                Garage.getGarage().ajouterVehicule(vehicule);
                JOptionPane.showMessageDialog(null, "Véhicule ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du véhicule: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            lireDonneesDepuisFichier();
        }
    }

}
