package Modele.Utilisateur;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class GestionUtilisateurs
{
    private static final Map<String, Utilisateur> utilisateurs = new HashMap<>();
    private static final String FILE_PATH = "utilisateurs.properties";

    public static Utilisateur seConnecter(String username, String password)
    {
        loadUserData();

        if (utilisateurs.containsKey(username))
        {
            Utilisateur utilisateur = utilisateurs.get(username);

            if (verifierMotDePasse(password, utilisateur.getMotDePasse(), utilisateur.getSalt()))
            {
                System.out.println("Connexion réussie pour " + username);
                return utilisateur;
            }
            else
            {
                System.out.println("Mot de passe incorrect pour " + username);
                return null;
            }
        }
        else
        {
            String salt = genererSalt();
            String motDePasseHash = hasherMotDePasse(password, salt);
            Utilisateur nouvelUtilisateur = new Utilisateur(username, motDePasseHash, salt);

            utilisateurs.put(username, nouvelUtilisateur);
            saveUserData();
            System.out.println("Nouvel utilisateur créé : " + username);
            return nouvelUtilisateur;
        }
    }

    private static void loadUserData()
    {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(FILE_PATH))
        {
            properties.load(fis);

            for (String username : properties.stringPropertyNames())
            {
                String[] parts = properties.getProperty(username).split(":");
                if (parts.length == 2) {
                    String motDePasseHash = parts[0];
                    String salt = parts[1];
                    utilisateurs.put(username, new Utilisateur(username, motDePasseHash, salt));
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    private static void saveUserData()
    {
        Properties properties = new Properties();
        for (Map.Entry<String, Utilisateur> entry : utilisateurs.entrySet())
        {
            properties.setProperty(entry.getKey(), entry.getValue().getMotDePasse() + ":" + entry.getValue().getSalt());
        }

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH))
        {
            properties.store(fos, "Utilisateurs enregistrés");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }

    private static String genererSalt()
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return bytesToHex(salt);
    }

    private static String hasherMotDePasse(String motDePasse, String salt)
    {
        try {
            PBEKeySpec spec = new PBEKeySpec(motDePasse.toCharArray(), salt.getBytes(), 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return bytesToHex(hash);
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            throw new RuntimeException("Erreur de hashage", e);
        }
    }

    private static boolean verifierMotDePasse(String motDePasse, String hashEnregistre, String salt)
    {
        String hashCalcule = hasherMotDePasse(motDePasse, salt);
        return hashCalcule.equals(hashEnregistre);
    }

    private static String bytesToHex(byte[] bytes)
    {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
