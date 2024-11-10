package Vue.InterfacesGraphiques;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GarageWindow extends JFrame {
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

    private static GarageWindow instance;
    public static GarageWindow getGarageWindow()
    {
        if(instance == null)
        {
            instance = new GarageWindow();
        }
        return instance;
    }


    public GarageWindow()
    {
        setTitle("Test");
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

        // Boutons d'actions
        String[] buttonNames = {"Ajouter", "Modifier", "Supprimer", "Voir", "Logout"};
        for (String name : buttonNames) {
            JButton button = new JButton(name);
            topPanel.add(button);
        }

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

    // Méthode pour lire les données depuis le fichier Vehicles.txt
    private void lireDonneesDepuisFichier() {
        try {
            // Appel de la méthode lireFichier() pour obtenir toutes les lignes du fichier
            List<String> lignes = lireFichier();

            // Parcourir chaque ligne et extraire les données
            for (String ligne : lignes) {
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
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    // La méthode lireFichier que vous avez définie
    public List<String> lireFichier() throws IOException {
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

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            GarageWindow frame = new GarageWindow();
            frame.setVisible(true);
        });
    }
}
