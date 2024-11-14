package Vue.MéthodesGarageWindow;

import Modèle.GestionDeDonnees.GestionFichier;
import Vue.InterfacesGraphiques.GarageWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MéthodesGarageWindow
{
    private static MéthodesGarageWindow instance;

    public static MéthodesGarageWindow getInstance()
    {
        if(instance == null)
        {
            instance = new MéthodesGarageWindow();
        }

        return instance;
    }

    public void lireDonneesDepuisFichier()
    {
        try
        {
            List<String> lignes = GestionFichier.getInstance().lireFichier();

            for (String ligne : lignes)
            {
                String[] elements = ligne.split(",");
                if (elements.length >= 8)
                {
                    String type = elements[0];
                    String marque = elements[1];
                    String modele = elements[2];
                    String puissance = elements[3];
                    String transmission = elements[4];
                    String pays = elements[5];
                    String annee = elements[6];
                    String cheminImage = elements[7];
                    var data = GarageWindow.getGarageWindow().getData();
                    data.add(new Object[]{type, marque, modele, puissance, transmission, pays, annee, getScaledImage(cheminImage)});
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
            if (type.equals("Tout") || row[0].equals(type))
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
            Image scaledImage = img.getScaledInstance(200, 60, Image.SCALE_SMOOTH); // Redimensionner l'image
            return new ImageIcon(scaledImage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public void RechargerTable()
    {
        // Lire les données depuis le fichier
        lireDonneesDepuisFichier();

        DefaultTableModel model = (DefaultTableModel) GarageWindow.getGarageWindow().getTable().getModel();
        model.setRowCount(0);

        loadTableData("Tout");

        model.fireTableDataChanged();
    }

}
