package ApplicationWeb;

import Modele.ClassesMetier.Vehicule;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Modele.GestionDeDonnees.GestionFichier.chargerVehiculesDepuisFichier;

public class PageHTMLGenerator
{
    private static final Logger logger = Logger.getLogger(PageHTMLGenerator.class.getName());
    public void genererPageHTML() throws IOException
    {
        // Charger la liste des véhicules en appelant votre méthode existante
        List<Vehicule> listeVehicules = chargerVehiculesDepuisFichier();

        // Générer le contenu HTML
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html><html lang=\"fr\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>Liste des véhicules</title>");
        htmlContent.append("<style>table { width: 100%; border-collapse: collapse; } table, th, td { border: 1px solid black; } th, td { padding: 8px; text-align: left; } th { background-color: #f2f2f2; }</style></head>");
        htmlContent.append("<body><h1>Liste des véhicules</h1><table><thead><tr><th>ID</th><th>Type</th><th>Marque</th><th>Modèle</th><th>Puissance</th><th>Transmission</th><th>Pays</th><th>Année</th><th>Image</th></tr></thead><tbody>");

        // Ajouter chaque véhicule au tableau HTML
        for (Vehicule vehicule : listeVehicules) {
            htmlContent.append("<tr>");
            htmlContent.append("<td>").append(vehicule.getIdentifiant()).append("</td>");
            htmlContent.append("<td>").append(vehicule.getType()).append("</td>");
            htmlContent.append("<td>").append(vehicule.getMarque()).append("</td>");
            htmlContent.append("<td>").append(vehicule.getModele()).append("</td>");
            htmlContent.append("<td>").append(vehicule.getPuissance()).append("</td>");
            htmlContent.append("<td>").append(vehicule.getTransmission()).append("</td>");
            htmlContent.append("<td>").append(vehicule.getPays()).append("</td>");
            htmlContent.append("<td>").append(vehicule.getAnnee()).append("</td>");
            htmlContent.append("<td><img src=\"").append(vehicule.getImage()).append("\" alt=\"").append(vehicule.getModele()).append("\" width=\"100\"></td>");
            htmlContent.append("</tr>");
        }

        // Fin du tableau et du fichier HTML
        htmlContent.append("</tbody></table></body></html>");

        // Enregistrer le fichier HTML
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./Vehicules/vehicules.html")))
        {
            writer.write(htmlContent.toString());
        }

        System.out.println("Page HTML générée avec succès !");
    }

    public static void main(String[] args)
    {
        try
        {
            new PageHTMLGenerator().genererPageHTML();
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE, "La page HTML n'a pas pu être créée");
        }
    }
}
