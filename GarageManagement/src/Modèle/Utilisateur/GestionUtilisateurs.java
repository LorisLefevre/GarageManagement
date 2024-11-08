package Modèle.Utilisateur;

import java.io.*;
import java.util.*;

public class GestionUtilisateurs
{
    private static Map<String, Utilisateur> utilisateurs = new HashMap<>();
    public static Utilisateur seConnecter(String username, String password)
    {
        loadUserData();

        if (utilisateurs.containsKey(username))
        {
            Utilisateur utilisateur = utilisateurs.get(username);
            if (utilisateur.getMotDePasse().equals(password))
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
            Utilisateur nouvelUtilisateur = new Utilisateur(username, password);
            utilisateurs.put(username, nouvelUtilisateur);
            saveUserData();
            System.out.println("Nouvel utilisateur créé : " + username);
            return nouvelUtilisateur;
        }
    }

    private static void loadUserData()
    {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("utilisateurs.properties"))
        {
            properties.load(fis);
            for (String username : properties.stringPropertyNames())
            {
                String motDePasse = properties.getProperty(username);
                utilisateurs.put(username, new Utilisateur(username, motDePasse));
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
            properties.setProperty(entry.getKey(), entry.getValue().getMotDePasse());
        }
        try (FileOutputStream fos = new FileOutputStream("utilisateurs.properties"))
        {
            properties.store(fos, "Utilisateurs enregistrés");
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        // Tests
        Utilisateur utilisateur = seConnecter("Loris", "LorisRace02");
        if (utilisateur != null)
        {
            utilisateur.Afficher();
        }

        Utilisateur nouvelUtilisateur = seConnecter("Alice", "alice2024");
        if (nouvelUtilisateur != null)
        {
            nouvelUtilisateur.Afficher();
        }
    }
}
