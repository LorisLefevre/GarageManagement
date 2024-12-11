package Vue.InterfacesGraphiques;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

public class VehiculeInformationWindow extends JFrame
{
    private static VehiculeInformationWindow instance;

    public static VehiculeInformationWindow getInstance()
    {
        if(instance == null)
        {
            instance = new VehiculeInformationWindow();
        }

        return instance;
    }

    private JLabel marque;

    public JLabel getMarque()
    {
        return marque;
    }

    public void setMarque(JLabel marque)
    {
        this.marque = marque;
    }

    private JLabel modele;

    public JLabel getModele()
    {
        return modele;
    }

    public void setModele(JLabel modele)
    {
        this.modele = modele;
    }

    private JLabel puissance;

    public JLabel getPuissance()
    {
        return puissance;
    }

    public void setPuissance(JLabel puissance)
    {
        this.puissance = puissance;
    }

    private JLabel transmission;

    public JLabel getTransmission()
    {
        return transmission;
    }

    public void setTransmission(JLabel transmission)
    {
        this.transmission = transmission;
    }

    private JLabel pays;

    public JLabel getPays()
    {
        return pays;
    }

    public void setPays(JLabel pays)
    {
        this.pays = pays;
    }

    private JLabel annee;

    public JLabel getAnnee()
    {
        return annee;
    }

    public void setAnnee(JLabel annee)
    {
        this.annee = annee;
    }

    private JLabel image;

    public JLabel getImage()
    {
        return image;
    }

    public void setImage(JLabel image)
    {
        this.image = image;
    }

    private JPanel imagePanel;

    public JPanel getImagePanel()
    {
        return imagePanel;
    }

    public void setImagePanel(JPanel imagePanel)
    {
        this.imagePanel = imagePanel;
    }

    public VehiculeInformationWindow() {
        super("Fenêtre d'information du véhicule");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel detailsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        marque = new JLabel("Marque : ");
        modele = new JLabel("Modèle : ");
        puissance = new JLabel("Puissance : ");
        transmission = new JLabel("Transmission : ");
        pays = new JLabel("Pays : ");
        annee = new JLabel("Année : ");

        detailsPanel.add(marque);
        detailsPanel.add(modele);
        detailsPanel.add(puissance);
        detailsPanel.add(transmission);
        detailsPanel.add(pays);
        detailsPanel.add(annee);

        imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        image = new JLabel("", SwingConstants.CENTER);
        image.setOpaque(true);
        image.setBackground(Color.LIGHT_GRAY); // Fond gris pour l'absence d'image
        imagePanel.add(image, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, detailsPanel, imagePanel);
        splitPane.setDividerLocation(100);
        splitPane.setResizeWeight(0.3);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);

        add(splitPane, BorderLayout.CENTER);
    }


    public void setVehiculeDetails(String marque, String modele, String puissance,
                                   String transmission, String pays, int annee, String imagePath)
    {
        this.marque.setText("Marque : " + marque);
        this.modele.setText("Modèle : " + modele);
        this.puissance.setText("Puissance : " + puissance);
        this.transmission.setText("Transmission : " + transmission);
        this.pays.setText("Pays : " + pays);
        this.annee.setText("Année : " + annee);

        try
        {
            BufferedImage image = null;

            if (imagePath.startsWith("http") || imagePath.startsWith("file:/"))
            {
                image = ImageIO.read(new URL(imagePath));
            }
            else
            {
                // Charge l'image depuis un fichier local ou une ressource
                image = ImageIO.read(new File(imagePath)); // Pour les chemins locaux
                // ou depuis les ressources du projet
                if (image == null) {
                    image = ImageIO.read(getClass().getResourceAsStream(imagePath));
                }
            }

            if (image != null)
            {
                ImageIcon icon = new ImageIcon(image.getScaledInstance(600, 300, Image.SCALE_SMOOTH));
                this.image.setIcon(icon);
                this.image.setText("");
            }
            else
            {
                throw new IllegalArgumentException("Image introuvable ou nulle.");
            }
        }
        catch (Exception e)
        {
            this.image.setText("Image introuvable ou invalide");
            this.image.setIcon(null);
            e.printStackTrace(); // Affiche les détails de l'erreur pour le débogage
        }
    }

    public void run()
    {
        this.getInstance();
        this.setVisible(true);
    }

}
