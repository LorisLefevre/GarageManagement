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

    public void AjouterVoiture(String marque, String modele, String puissance, String transmission, int annee, String pays, String image)
    {
        String sql = "INSERT INTO voiture (marque, modele, puissance, transmission, annee, pays, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
                System.out.println("Voiture ajoutée avec succès !");
            }
            else
            {
                System.out.println("Erreur lors de l'ajout de la voiture.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void AjouterMoto(String marque, String modele, String puissance, String transmission, int annee, String pays, String image)
    {
        String sql = "INSERT INTO moto (marque, modele, puissance, transmission, annee, pays, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
                System.out.println("Moto ajoutée avec succès !");
            }
            else
            {
                System.out.println("Erreur lors de l'ajout de la moto.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void AjouterCamionnette(String marque, String modele, String puissance, String transmission, int annee, String pays, String image)
    {
        String sql = "INSERT INTO camionnette (marque, modele, puissance, transmission, annee, pays, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
                System.out.println("Camionnette ajoutée avec succès !");
            }
            else
            {
                System.out.println("Erreur lors de l'ajout de la camionnette.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void AjouterCamion(String marque, String modele, String puissance, String transmission, int annee, String pays, String image)
    {
        String sql = "INSERT INTO camion (marque, modele, puissance, transmission, annee, pays, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
                System.out.println("Camion ajouté avec succès !");
            }
            else
            {
                System.out.println("Erreur lors de l'ajout du camion.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void SupprimerVoiture(String marque, String modele)
    {
        String sql = "DELETE FROM voiture WHERE marque = ? AND modele = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, marque);
            statement.setString(2, modele);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("Voiture supprimée avec succès !");
            }
            else
            {
                System.out.println("Aucune voiture correspondante n'a été trouvée.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void SupprimerMoto(String marque, String modele)
    {
        String sql = "DELETE FROM moto WHERE marque = ? AND modele = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, marque);
            statement.setString(2, modele);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("Moto supprimée avec succès !");
            }
            else
            {
                System.out.println("Aucune moto correspondante n'a été trouvée.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void SupprimerCamionnette(String marque, String modele)
    {
        String sql = "DELETE FROM camionnette WHERE marque = ? AND modele = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, marque);
            statement.setString(2, modele);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("Camionnette supprimée avec succès !");
            }
            else
            {
                System.out.println("Aucune camionnette correspondante n'a été trouvée.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void SupprimerCamion(String marque, String modele)
    {
        String sql = "DELETE FROM camion WHERE marque = ? AND modele = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, marque);
            statement.setString(2, modele);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("Camion supprimé avec succès !");
            }
            else
            {
                System.out.println("Aucun camion correspondant n'a été trouvé.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Requetes requetes = new Requetes();

        //requetes.AjouterVoiture("Ford", "Escort", 110, "Manuelle", 2003, "États-Unis", null);
        //requetes.AjouterCamionnette("Fiat", "Ducato", 140, "Manuelle", 2008, "Italie", null);

        requetes.SupprimerVoiture("Toyota", "Corolla");
    }
}
