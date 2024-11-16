package Modèle.GestionBaseDeDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Requetes
{
    private static final String URL = "jdbc:h2:./vehicule";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static Requetes instance;

    public static Requetes getInstance()
    {
        if(instance == null)
        {
            instance = new Requetes();
        }

        return instance;
    }
    public void AjouterVehicule(String typeVehicule, String marque, String modele, String puissance, String transmission, int annee, String pays, String image)
    {
        String sql = "INSERT INTO " + typeVehicule + " (marque, modele, puissance, transmission, annee, pays, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, marque);
            statement.setString(2, modele);
            statement.setString(3, puissance);
            statement.setString(4, transmission);
            statement.setInt(5, annee);
            statement.setString(6, pays);
            statement.setString(7, image);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println(typeVehicule + " ajoutée avec succès !");
            }
            else
            {
                System.out.println("Erreur lors de l'ajout du " + typeVehicule + ".");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void SupprimerVehicule(String type, String marque, String modele)
    {
        String sql = "DELETE FROM " + type + " WHERE marque = ? AND modele = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, marque);
            statement.setString(2, modele);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("Le/La " + type + " a supprimé(e) avec succès !");
            }
            else
            {
                System.out.println("Aucun véhicule du type " + type + " n'a été trouvé");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
