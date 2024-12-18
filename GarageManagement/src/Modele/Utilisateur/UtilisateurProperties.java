package Modele.Utilisateur;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilisateurProperties
{
    private static final Logger logger = Logger.getLogger(UtilisateurProperties.class.getName());
    public static void main(String[] args)
    {

        Properties properties = new Properties();
        properties.setProperty("aaa", "aaa");

        String filePath = "utilisateurs.properties";

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath))
        {
            properties.store(fileOutputStream, "Liste des utilisateurs");
            System.out.println("Fichier properties créé avec succès !");
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de la création du fichier properties : " + e.getMessage());
            logger.log(Level.WARNING, "Erreur lors de la création du fichier properties");
        }
    }
}
