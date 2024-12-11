package Modèle.GestionDeDonnees;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationModeTravail
{
    private static final String CONFIG_FILE = "config.properties";

    public static String getProperty(String key) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
            return properties.getProperty(key);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier config : " + e.getMessage());
        }
        return null;
    }

    public static void setProperty(String key, String value)
    {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE))
        {
            properties.load(fis);
        }
        catch (IOException ignored) {
            // Fichier non trouvé ou vide, on crée un nouveau fichier
        }
        properties.setProperty(key, value);
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            properties.store(fos, "Configuration de l'application");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier config : " + e.getMessage());
        }
    }
}
