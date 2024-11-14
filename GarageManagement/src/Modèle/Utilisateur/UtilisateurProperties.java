package Modèle.Utilisateur;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilisateurProperties
{
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
            e.printStackTrace();
        }
    }
}
