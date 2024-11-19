package Modèle.GestionBaseDeDonnees;

import Modèle.ClassesMetier.Vehicule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void SupprimerVehicule(int id, String type)
    {
        String sql = "DELETE FROM " + type + " WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql))
        {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0)
            {
                System.out.println("Le/La " + type + " a été supprimé(e) avec succès !");
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

    public void modifierVehicule(int id, Vehicule vehicule) throws SQLException
    {
        String sql = "UPDATE " + vehicule.getType() + " SET marque = ?, modele = ?, puissance = ?, " +
                "transmission = ?, pays = ?, annee = ?, image = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, vehicule.getMarque());
            statement.setString(2, vehicule.getModele());
            statement.setString(3, vehicule.getPuissance());
            statement.setString(4, vehicule.getTransmission());
            statement.setString(5, vehicule.getPays());
            statement.setInt(6, vehicule.getAnnee());
            statement.setString(7, vehicule.getImage());
            statement.setInt(8, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 0)
            {
                throw new SQLException("Aucun véhicule trouvé avec cet ID.");
            }
        }
        catch (SQLException e)
        {
            System.err.println("Erreur lors de la modification du véhicule : " + e.getMessage());
            throw e;
        }
    }

    public Vehicule rechercherVehiculeParId(String typeTable, int id) throws SQLException
    {
        String sql = "SELECT * FROM " + typeTable + " WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql))
        {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    Vehicule vehicule = new Vehicule();
                    vehicule.setIdentifiant(resultSet.getInt("id"));
                    vehicule.setType(typeTable);
                    vehicule.setMarque(resultSet.getString("marque"));
                    vehicule.setModele(resultSet.getString("modele"));
                    vehicule.setPuissance(resultSet.getString("puissance"));
                    vehicule.setTransmission(resultSet.getString("transmission"));
                    vehicule.setAnnee(resultSet.getInt("annee"));
                    vehicule.setPays(resultSet.getString("pays"));
                    vehicule.setImage(resultSet.getString("image"));
                    return vehicule;
                }
                else
                {
                    return null;
                }
            }
        }
    }

    public List<Vehicule> RecupererVoitures() throws SQLException
    {
        String sql = "SELECT * FROM voiture";
        List<Vehicule> vehicules = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                int id = rs.getInt("id");
                String marque = rs.getString("marque");
                String modele = rs.getString("modele");
                String puissance = rs.getString("puissance");
                String transmission = rs.getString("transmission");
                String pays = rs.getString("pays");
                String image = rs.getString("image");

                int annee = rs.getInt("annee");

                Vehicule vehicule = new Vehicule(id, marque, modele, puissance, transmission, pays, annee, image, "Voiture");
                vehicules.add(vehicule);
            }
        }
        return vehicules;
    }
}
