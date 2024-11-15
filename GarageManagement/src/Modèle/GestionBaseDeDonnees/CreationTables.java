package Modèle.GestionBaseDeDonnees;

import java.sql.*;

public class CreationTables
{
    private static final String URL = "jdbc:h2:./vehicule";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static CreationTables instance;

    public static CreationTables getInstance()
    {
        if(instance == null)
        {
            instance = new CreationTables();
        }

        return instance;
    }

    public void CreationBase()
    {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement())
        {
            System.out.println("Base de données 'vehicule' créée ou déjà existante.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void CreationTableVoiture()
    {
        String sql = "CREATE TABLE IF NOT EXISTS voiture (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "marque VARCHAR(50), " +
                "modele VARCHAR(50), " +
                "puissance INT, " +
                "transmission VARCHAR(20), " +
                "annee INT, " +
                "pays VARCHAR(50), " +
                "image VARCHAR(255)" +
                ")";
        ExecuterRequeteCreationTable(sql, "Table 'voiture' créée.");
    }

    public void CreationTableMoto()
    {
        String sql = "CREATE TABLE IF NOT EXISTS moto (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "marque VARCHAR(50), " +
                "modele VARCHAR(50), " +
                "puissance INT, " +
                "transmission VARCHAR(20), " +
                "annee INT, " +
                "pays VARCHAR(50), " +
                "image VARCHAR(255)" +
                ")";
        ExecuterRequeteCreationTable(sql, "Table 'moto' créée.");
    }

    public void CreationTableCamionnette()
    {
        String sql = "CREATE TABLE IF NOT EXISTS camionnette (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "marque VARCHAR(50), " +
                "modele VARCHAR(50), " +
                "puissance INT, " +
                "transmission VARCHAR(20), " +
                "annee INT, " +
                "pays VARCHAR(50), " +
                "image VARCHAR(255)" +
                ")";
        ExecuterRequeteCreationTable(sql, "Table 'camionnette' créée.");
    }

    public void CreationTableCamion()
    {
        String sql = "CREATE TABLE IF NOT EXISTS camion (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "marque VARCHAR(50), " +
                "modele VARCHAR(50), " +
                "puissance INT, " +
                "transmission VARCHAR(20), " +
                "annee INT, " +
                "pays VARCHAR(50), " +
                "image VARCHAR(255)" +
                ")";
        ExecuterRequeteCreationTable(sql, "Table 'camion' créée.");
    }

    public void ExecuterRequeteCreationTable(String sql, String message)
    {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement())
        {
            statement.executeUpdate(sql);
            System.out.println(message);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void ajouterVoiture(String marque, String modele, int puissance, String transmission, int annee, String pays, String image) {
        String sql = "INSERT INTO voiture (marque, modele, puissance, transmission, annee, pays, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Remplir les paramètres de la requête
            statement.setString(1, marque);
            statement.setString(2, modele);
            statement.setInt(3, puissance);
            statement.setString(4, transmission);
            statement.setInt(5, annee);
            statement.setString(6, pays);
            statement.setString(7, image);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Voiture ajoutée avec succès !");
            } else {
                System.out.println("Erreur lors de l'ajout de la voiture.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        CreationTables creationTables = new CreationTables();

        creationTables.CreationBase();
        creationTables.CreationTableVoiture();
        creationTables.CreationTableMoto();
        creationTables.CreationTableCamionnette();
        creationTables.CreationTableCamion();


        creationTables.ajouterVoiture("Toyota", "Corolla", 120, "Manuelle", 2020, "Japon", null);
    }
}
