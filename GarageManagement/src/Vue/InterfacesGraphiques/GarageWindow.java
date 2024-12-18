package Vue.InterfacesGraphiques;

import Controleur.ActionsControleur;
import Controleur.Controleur;
import Vue.MethodesGarageWindow.ChoixUtilisateur;
import Vue.Vues.VueGarageWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GarageWindow extends JFrame implements VueGarageWindow
{
    private final DefaultTableModel model;

    public DefaultTableModel getModel()
    {
        return model;
    }
    private final List<Object[]> data;

    public List<Object[]> getData()
    {
        return data;
    }

    private final JTable table;

    public JTable getTable()
    {
        return table;
    }

    private final JLabel userLabel;

    public void setUserLabel(String user)
    {
        userLabel.setText("Utilisateur : " + user);
    }

    private final JLabel messageLabel;
    private static GarageWindow instance;
    public static GarageWindow getGarageWindow()
    {
        if(instance == null)
        {
            instance = new GarageWindow();
        }
        return instance;
    }

    public static void resetGarageWindow()
    {
        instance = null;
    }


    private final JButton Ajouter;
    private final JButton Modifier;
    private final JButton Supprimer;
    private final JButton Afficher;
    private final JButton Voir;
    private final JButton Changer;

    public JButton getChanger()
    {
        return Changer;
    }

    private final JComboBox<String> Trier;
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

        userLabel = new JLabel();
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(userLabel);

        Ajouter = new JButton("Ajouter");
        Modifier = new JButton("Modifier");
        Supprimer = new JButton("Supprimer");
        Afficher = new JButton("Afficher");
        Voir = new JButton("Voir");
        Changer = new JButton("Changer");
        Trier = new JComboBox<>(new String[]{"Tout", "Voiture", "Moto", "Camionnette", "Camion"});

        topPanel.add(Ajouter);
        topPanel.add(Modifier);
        topPanel.add(Supprimer);
        //topPanel.add(Afficher);
        topPanel.add(Voir);
        topPanel.add(Changer);
        topPanel.add(new JLabel("Trier par : "));
        topPanel.add(Trier);

        add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Id", "Type", "Marque", "Modele", "Puissance", "Transmission", "Pays", "Année", "Image"};
        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);

        table.getColumn("Image").setCellRenderer(new ImageRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        messageLabel = new JLabel("");
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
    public void setControleur(Controleur controleur)
    {
        Ajouter.setActionCommand(ActionsControleur.AJOUTER);
        Modifier.setActionCommand(ActionsControleur.MODIFIER);
        Supprimer.setActionCommand(ActionsControleur.SUPPRIMER);
        Afficher.setActionCommand(ActionsControleur.AFFICHER);
        Voir.setActionCommand(ActionsControleur.VOIR);
        Changer.setActionCommand(ActionsControleur.CHANGER);
        Trier.setActionCommand(ActionsControleur.TRIER);

        Ajouter.addActionListener(controleur);
        Modifier.addActionListener(controleur);
        Supprimer.addActionListener(controleur);
        Afficher.addActionListener(controleur);
        Voir.addActionListener(controleur);
        Changer.addActionListener(controleur);
        Trier.addActionListener(controleur);
    }

    public void Ajouter()
    {
        ChoixUtilisateur.getInstance().ChoixAjout();
        messageLabel.setText("Véhicule ajouté");
    }

    public void Supprimer()
    {
        ChoixUtilisateur.getInstance().ChoixSuppression();
        messageLabel.setText("Véhicule supprimé");
    }

    public void Modifier()
    {
        ChoixUtilisateur.getInstance().ChoixModification();
        messageLabel.setText("Véhicule modifié");
    }
    public void Trier()
    {
        ChoixUtilisateur.getInstance().ChoixTri();
        messageLabel.setText("Tri effectué");
    }

    public void Afficher()
    {
        ChoixUtilisateur.getInstance().ChoixAffichage();
        VehiculeInformationWindow.getInstance().setVisible(false);
        messageLabel.setText("Affichage des véhicules");
    }

    public void Voir()
    {
        ChoixUtilisateur.getInstance().ChoixVision();
        messageLabel.setText("Voici le véhicule en question");
    }

    public void Changer()
    {
        ChoixUtilisateur.getInstance().ResetModeTravail();
        ChoixUtilisateur.getInstance().ChoixMode();
        Afficher();
    }

    public void ChargementDonnees()
    {
        Afficher();
    }

}
