package Vue.MethodesGarageWindow;

import Modele.ClassesMetier.Vehicule;
import Modele.GestionBaseDeDonnees.Requetes;
import Modele.GestionDeDonnees.GestionFichier;
import Vue.InterfacesGraphiques.GarageWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.*;

public class MethodesGarageWindow
{
    private static final Logger logger = Logger.getLogger(MethodesGarageWindow.class.getName());
    private static MethodesGarageWindow instance;

    public static MethodesGarageWindow getInstance()
    {
        if(instance == null)
        {
            instance = new MethodesGarageWindow();
        }

        return instance;
    }

    public void lireDonneesDepuisFichier()
    {
        try
        {
            GestionFichier.getInstance();
            List<String> lignes = GestionFichier.lireFichier();

            for (String ligne : lignes)
            {
                String[] elements = ligne.split(",");
                if (elements.length >= 9)
                {
                    String id = elements[0];
                    String type = elements[1];
                    String marque = elements[2];
                    String modele = elements[3];
                    String puissance = elements[4];
                    String transmission = elements[5];
                    String pays = elements[6];
                    String annee = elements[7];
                    String cheminImage = elements[8];
                    var data = GarageWindow.getGarageWindow().getData();
                    data.add(new Object[]{id,type, marque, modele, puissance, transmission, pays, annee, getScaledImage(cheminImage)});
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public void filterTableByType(String type)
    {
        loadTableData(type);
    }

    public void loadTableData(String type)
    {
        DefaultTableModel model = GarageWindow.getGarageWindow().getModel();
        List<Object[]> data = GarageWindow.getGarageWindow().getData();
        model.setRowCount(0);

        for (Object[] row : data)
        {
            if (type.equals("Tout") || row[1].equals(type))
            {
                model.addRow(row);
            }
        }
    }

    private ImageIcon getScaledImage(String path)
    {
        try
        {
            BufferedImage img = ImageIO.read(new File(path));
            Image scaledImage = img.getScaledInstance(200, 60, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }
        catch (Exception e)
        {
            logger.log(Level.WARNING, "Problème de redimensionnement des images");
            return null;
        }
    }
    public void RechargerTable()
    {
        GarageWindow.getGarageWindow().getData().clear();

        lireDonneesDepuisFichier();

        DefaultTableModel model = (DefaultTableModel) GarageWindow.getGarageWindow().getTable().getModel();
        model.setRowCount(0);

        loadTableData("Tout");

        model.fireTableDataChanged();
    }

    public void RechargerTableBD(String type)
    {
        try
        {
            List<Vehicule> vehicules;

            if (type == null || type.equals("Tout"))
            {
                vehicules = Requetes.getInstance().RecupererVehicules();
            }
            else
            {
                vehicules = Requetes.getInstance().RecupererVehiculesParType(type);
            }

            GarageWindow.getGarageWindow().getData().clear();

            GarageWindow.getGarageWindow().getModel().setRowCount(0);

            for (Vehicule vehicule : vehicules)
            {
                ImageIcon icon = null;
                String imagePath = vehicule.getImage();
                if (imagePath != null && !imagePath.isEmpty())
                {
                    icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                }

                GarageWindow.getGarageWindow().getModel().addRow(new Object[]{
                        vehicule.getIdentifiant(),
                        vehicule.getType(),
                        vehicule.getMarque(),
                        vehicule.getModele(),
                        vehicule.getPuissance(),
                        vehicule.getTransmission(),
                        vehicule.getPays(),
                        vehicule.getAnnee(),
                        icon
                });
            }
        }

        catch(SQLException e)
        {
            logger.log(Level.SEVERE, "Les véhicules n'ont pas pu être récupérés");
            GarageWindow.getGarageWindow().showMessage("Erreur lors de la récupération des véhicule : " + e.getMessage());
        }

    }
}
