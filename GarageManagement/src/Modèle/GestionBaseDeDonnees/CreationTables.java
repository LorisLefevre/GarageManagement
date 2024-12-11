package Modèle.GestionBaseDeDonnees;

import java.sql.*;

public class CreationTables
{
    private static final String URL_SQL = "jdbc:mysql://localhost:3306/Loris_Perso?autoReconnect=true&useSSL=false";
    private static final String USER_SQL = "root";
    private static final String PASSWORD_SQL = "LorisRace02.";
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

    public void CreationTableVehicule()
    {

        String sql = "CREATE TABLE IF NOT EXISTS vehicule (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "type VARCHAR(50), " +
                "marque VARCHAR(50), " +
                "modele VARCHAR(50), " +
                "puissance VARCHAR(10), " +
                "transmission VARCHAR(20), " +
                "annee INT, " +
                "pays VARCHAR(50), " +
                "image VARCHAR(255)" +
                ")";
        ExecuterRequeteCreationTable(sql, "Table 'vehicule' créée.");
    }

    public void ExecuterRequeteCreationTable(String sql, String message)
    {
        try (Connection connection = DriverManager.getConnection(URL_SQL, USER_SQL, PASSWORD_SQL);
             Statement statement = connection.createStatement())
        {
            statement.executeUpdate(sql);
            System.out.println("MySQL: " + message);
        }
        catch (SQLException mysqlException)
        {
            System.err.println("Échec de la connexion à MySQL. Tentative de connexion à la base H2.");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement())
            {
                statement.executeUpdate(sql);
                System.out.println("H2: " + message);
            }
            catch (SQLException h2Exception)
            {
                System.err.println("Échec de la connexion à H2.");
                h2Exception.printStackTrace();
            }
        }
    }


    public static void main(String[] args)
    {
        CreationTables creationTables = new CreationTables();

        //creationTables.CreationBase();
        creationTables.CreationTableVehicule();

    }
}
