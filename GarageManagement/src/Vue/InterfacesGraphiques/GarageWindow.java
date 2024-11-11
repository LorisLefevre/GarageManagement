package Vue.InterfacesGraphiques;

import Contrôleur.ActionsControleur;
import Contrôleur.Controleur;
import Modèle.GestionDeDonnees.Garage;
import Vue.MéthodesGarageWindow.MéthodesBoutonsGarageWindow;
import Vue.MéthodesGarageWindow.MéthodesGarageWindow;
import Vue.Vues.VueGarageWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GarageWindow extends JFrame implements VueGarageWindow
{
    private DefaultTableModel model;

    public DefaultTableModel getModel()
    {
        return model;
    }
    private List<Object[]> data;

    public List<Object[]> getData()
    {
        return data;
    }

    private JTable table;

    public JTable getTable()
    {
        return table;
    }

    private JLabel userLabel;

    public JLabel getUserLabel()
    {
        return  userLabel;
    }

    public void setUserLabel(String user)
    {
        userLabel.setText("Utilisateur : " + user);
    }

    private JLabel messageLabel;

    public JLabel getMessageLabel()
    {
        return messageLabel;
    }

    public void setMessageLabel(String message)
    {
        messageLabel.setText("");
    }

    private static final String FICHIER_VEHICULES = "Vehicles.txt"; // Chemin vers le fichier

    private Garage garage;
    private MéthodesBoutonsGarageWindow méthodeBoutonGarageWindow;

    private MéthodesGarageWindow méthodesGarageWindow;
    private static GarageWindow instance;
    public static GarageWindow getGarageWindow()
    {
        if(instance == null)
        {
            instance = new GarageWindow();
            MéthodesGarageWindow.getInstance().lireDonneesDepuisFichier();
            MéthodesGarageWindow.getInstance().loadTableData("Tout");
        }
        return instance;
    }

    private JButton Ajouter;
    private JButton Modifier;
    private JButton Supprimer;
    private JButton Voir;

    private JComboBox<String> Trier;
    public JComboBox<String> getTrier()
    {
        return Trier;
    }

    public GarageWindow()
    {
        setTitle("Garage Window");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        data = new ArrayList<>();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Étiquette utilisateur
        userLabel = new JLabel();
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(userLabel);

        Ajouter = new JButton("Ajouter");
        Modifier = new JButton("Modifier");
        Supprimer = new JButton("Supprimer");
        Voir = new JButton("Voir");
        Trier = new JComboBox<>(new String[]{"Tout", "Voiture", "Moto", "Camionnette", "Camion"});

        topPanel.add(Ajouter);
        topPanel.add(Modifier);
        topPanel.add(Supprimer);
        topPanel.add(Voir);
        topPanel.add(new JLabel("Trier par : "));
        topPanel.add(Trier);

        add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Type", "Marque", "Modèle", "Puissance", "Transmission", "Pays", "Année", "Image"};
        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);

        table.getColumn("Image").setCellRenderer(new ImageRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        messageLabel = new JLabel("Données de véhicules affichées avec succès");
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(messageLabel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    static class ImageRenderer extends JLabel implements TableCellRenderer
    {
        public ImageRenderer()
        {
            setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column)
        {
            if (value instanceof ImageIcon)
            {
                setIcon((ImageIcon) value);
            }
            else
            {
                setText(value != null ? value.toString() : "");
            }
            return this;
        }
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
        Trier.setActionCommand(ActionsControleur.TRIER);

        Ajouter.addActionListener(controleur);
        Modifier.addActionListener(controleur);
        Supprimer.addActionListener(controleur);
        Voir.addActionListener(controleur);
        Trier.addActionListener(controleur);
    }

    public void Ajouter()
    {
        showMessage("Ajout de véhicule");
        MéthodesBoutonsGarageWindow.getInstance().BoutonAjouter();
    }

    public void Modifier()
    {
        showMessage("Modification d'un véhicule");
        MéthodesBoutonsGarageWindow.getInstance().BoutonModifier();
    }

    public void Trier()
    {
        showMessage("Tri de véhicules par type");
        MéthodesBoutonsGarageWindow.getInstance().BoutonTrier();
    }
}
