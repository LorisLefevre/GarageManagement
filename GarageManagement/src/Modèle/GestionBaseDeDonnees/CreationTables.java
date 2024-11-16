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

    public void SupprimerTable(String Nom)
    {
        String sql = "DROP TABLE IF EXISTS " + Nom;

        ExecuterRequeteCreationTable(sql, "Table Supprimée");
    }

    public void CreationTableVoiture()
    {
        SupprimerTable("voiture");

        String sql = "CREATE TABLE IF NOT EXISTS voiture (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "marque VARCHAR(50), " +
                "modele VARCHAR(50), " +
                "puissance VARCHAR(10), " +
                "transmission VARCHAR(20), " +
                "annee INT, " +
                "pays VARCHAR(50), " +
                "image VARCHAR(255)" +
                ")";
        ExecuterRequeteCreationTable(sql, "Table 'voiture' créée.");
    }

    public void CreationTableMoto()
    {
        SupprimerTable("moto");

        String sql = "CREATE TABLE IF NOT EXISTS moto (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "marque VARCHAR(50), " +
                "modele VARCHAR(50), " +
                "puissance VARCHAR(10), " +
                "transmission VARCHAR(20), " +
                "annee INT, " +
                "pays VARCHAR(50), " +
                "image VARCHAR(255)" +
                ")";
        ExecuterRequeteCreationTable(sql, "Table 'moto' créée.");
    }

    public void CreationTableCamionnette()
    {
        SupprimerTable("camionnette");

        String sql = "CREATE TABLE IF NOT EXISTS camionnette (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "marque VARCHAR(50), " +
                "modele VARCHAR(50), " +
                "puissance VARCHAR(10), " +
                "transmission VARCHAR(20), " +
                "annee INT, " +
                "pays VARCHAR(50), " +
                "image VARCHAR(255)" +
                ")";
        ExecuterRequeteCreationTable(sql, "Table 'camionnette' créée.");
    }

    public void CreationTableCamion()
    {
        SupprimerTable("camion");

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


    public static void main(String[] args)
    {
        CreationTables creationTables = new CreationTables();

        creationTables.CreationBase();
        creationTables.CreationTableVoiture();
        creationTables.CreationTableMoto();
        creationTables.CreationTableCamionnette();
        creationTables.CreationTableCamion();

    }
}
