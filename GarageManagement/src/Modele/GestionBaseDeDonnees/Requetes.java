package Modele.GestionBaseDeDonnees;

import Modele.ClassesMetier.Vehicule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Requetes
{
    private static final String URL_SQL = "jdbc:mysql://localhost:3306/Loris_Perso?autoReconnect=true&useSSL=false";
    private static final String USER_SQL = "root";
    private static final String PASSWORD_SQL = "LorisRace02.";
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
    public void AjouterVehicule(String type, String marque, String modele, String puissance, String transmission, int annee, String pays, String image) {
        String sql = "INSERT INTO vehicule (type, marque, modele, puissance, transmission, annee, pays, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String[] urls = {
                URL_SQL,
                URL
        };
        String[] users = {
                USER_SQL,
                USER
        };
        String[] passwords = {
                PASSWORD_SQL,
                PASSWORD
        };

        SQLException lastException = null;


        for (int i = 0; i < urls.length; i++)
        {
            try (Connection connection = DriverManager.getConnection(urls[i], users[i], passwords[i]);
                 PreparedStatement statement = connection.prepareStatement(sql))
            {

                statement.setString(1, type);
                statement.setString(2, marque);
                statement.setString(3, modele);
                statement.setString(4, puissance);
                statement.setString(5, transmission);
                statement.setInt(6, annee);
                statement.setString(7, pays);
                statement.setString(8, image);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0)
                {
                    System.out.println(type + " ajoutée avec succès dans la base : " + urls[i]);
                }
                else
                {
                    System.out.println("Erreur lors de l'ajout du " + type + ".");
                }

                return;

            } catch (SQLException e)
            {
                lastException = e;
            }
        }

        if (lastException != null)
        {
            System.err.println("Échec de l'ajout dans toutes les bases.");
            throw new RuntimeException("Impossible d'ajouter le véhicule.", lastException);
        }
    }


    public void SupprimerVehicule(int id)
    {
        String sql = "DELETE FROM vehicule WHERE id = ?";

        String[] urls = {
                URL_SQL,
                URL
        };
        String[] users = {
                USER_SQL,
                USER
        };
        String[] passwords = {
                PASSWORD_SQL,
                PASSWORD
        };
        SQLException lastException = null;

        for (int i = 0; i < urls.length; i++)
        {
            try (Connection connection = DriverManager.getConnection(urls[i], users[i], passwords[i]);
                 PreparedStatement statement = connection.prepareStatement(sql))
            {

                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0)
                {
                    System.out.println("Le véhicule a été supprimé(e) avec succès dans la base : " + urls[i]);
                }
                else
                {
                    System.out.println("Aucun véhicule trouvé avec cet ID dans la base : " + urls[i]);
                }
                return;
            }
            catch (SQLException e)
            {
                lastException = e;
            }
        }

        if (lastException != null)
        {
            System.err.println("Erreur lors de la suppression dans toutes les bases.");
            throw new RuntimeException("Impossible de supprimer le véhicule.", lastException);
        }
    }


    public void modifierVehicule(int id, Vehicule vehicule) throws SQLException
    {
        String sql = "UPDATE vehicule SET type = ?, marque = ?, modele = ?, puissance = ?, " +
                "transmission = ?, pays = ?, annee = ?, image = ? WHERE id = ?";

        String[] urls = {
                URL_SQL,
                URL
        };
        String[] users = {
                USER_SQL,
                USER
        };
        String[] passwords = {
                PASSWORD_SQL,
                PASSWORD
        };

        SQLException lastException = null;

        for (int i = 0; i < urls.length; i++)
        {
            try (Connection connection = DriverManager.getConnection(urls[i], users[i], passwords[i]);
                 PreparedStatement statement = connection.prepareStatement(sql))
            {

                statement.setString(1, vehicule.getType());
                statement.setString(2, vehicule.getMarque());
                statement.setString(3, vehicule.getModele());
                statement.setString(4, vehicule.getPuissance());
                statement.setString(5, vehicule.getTransmission());
                statement.setString(6, vehicule.getPays());
                statement.setInt(7, vehicule.getAnnee());
                statement.setString(8, vehicule.getImage());
                statement.setInt(9, id);

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0)
                {
                    System.out.println("Véhicule modifié(e) avec succès dans la base : " + urls[i]);
                    return;
                }
                else
                {
                    System.out.println("Aucun véhicule trouvé avec cet ID dans la base : " + urls[i]);
                }
            } catch (SQLException e)
            {
                lastException = e;
            }
        }

        if (lastException != null)
        {
            System.err.println("Erreur lors de la modification dans toutes les bases.");
            throw new RuntimeException("Impossible de modifier le véhicule.", lastException);
        }
    }


    public Vehicule rechercherVehiculeParId(int id) throws SQLException
    {
        String sql = "SELECT * FROM vehicule WHERE id = ?";

        String[] urls = {
                URL_SQL,
                URL
        };
        String[] users = {
                USER_SQL,
                USER
        };
        String[] passwords = {
                PASSWORD_SQL,
                PASSWORD
        };

        SQLException lastException = null;

        for (int i = 0; i < urls.length; i++) {
            try (Connection connection = DriverManager.getConnection(urls[i], users[i], passwords[i]);
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery())
                {
                    if (resultSet.next())
                    {
                        Vehicule vehicule = new Vehicule();
                        vehicule.setIdentifiant(resultSet.getInt("id"));
                        vehicule.setType(resultSet.getString("type"));
                        vehicule.setMarque(resultSet.getString("marque"));
                        vehicule.setModele(resultSet.getString("modele"));
                        vehicule.setPuissance(resultSet.getString("puissance"));
                        vehicule.setTransmission(resultSet.getString("transmission"));
                        vehicule.setAnnee(resultSet.getInt("annee"));
                        vehicule.setPays(resultSet.getString("pays"));
                        vehicule.setImage(resultSet.getString("image"));

                        System.out.println("Véhicule trouvé dans la base : " + urls[i]);
                        return vehicule;
                    }
                }
            } catch (SQLException e)
            {
                lastException = e;
            }
        }

        if (lastException != null)
        {
            System.err.println("Erreur lors de la recherche dans toutes les bases.");
            throw new RuntimeException("Impossible de trouver le véhicule.", lastException);
        }

        return null;
    }


    public List<Vehicule> RecupererVehicules() throws SQLException
    {
        String sql = "SELECT * FROM vehicule";
        List<Vehicule> vehicules = new ArrayList<>();


        String[] urls = {
                URL_SQL,
                URL
        };
        String[] users = {
                USER_SQL,
                USER
        };
        String[] passwords = {
                PASSWORD_SQL,
                PASSWORD
        };

        SQLException lastException = null;

        for (int i = 0; i < urls.length; i++)
        {
            try (Connection connection = DriverManager.getConnection(urls[i], users[i], passwords[i]);
                 Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql))
            {

                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String type = rs.getString("type");
                    String marque = rs.getString("marque");
                    String modele = rs.getString("modele");
                    String puissance = rs.getString("puissance");
                    String transmission = rs.getString("transmission");
                    String pays = rs.getString("pays");
                    String image = rs.getString("image");
                    int annee = rs.getInt("annee");

                    Vehicule vehicule = new Vehicule(id, marque, modele, puissance, transmission, pays, annee, image, type);
                    vehicules.add(vehicule);
                }

                return vehicules;
            }
            catch (SQLException e)
            {
                lastException = e;
            }
        }

        if (lastException != null)
        {
            throw lastException;
        }

        return vehicules;
    }

    public List<Vehicule> RecupererVehiculesParType(String type) throws SQLException
    {
        String sql = "SELECT * FROM vehicule WHERE type = ?";
        List<Vehicule> vehicules = new ArrayList<>();

        String[] urls = {
                URL_SQL,
                URL
        };
        String[] users = {
                USER_SQL,
                USER
        };
        String[] passwords = {
                PASSWORD_SQL,
                PASSWORD
        };

        SQLException lastException = null;

        for (int i = 0; i < urls.length; i++)
        {
            try (Connection connection = DriverManager.getConnection(urls[i], users[i], passwords[i]);
                 PreparedStatement pstmt = connection.prepareStatement(sql))
            {
                pstmt.setString(1, type);

                try (ResultSet rs = pstmt.executeQuery())
                {
                    while (rs.next())
                    {
                        int id = rs.getInt("id");
                        String typeVehicule = rs.getString("type");
                        String marque = rs.getString("marque");
                        String modele = rs.getString("modele");
                        String puissance = rs.getString("puissance");
                        String transmission = rs.getString("transmission");
                        String pays = rs.getString("pays");
                        String image = rs.getString("image");
                        int annee = rs.getInt("annee");

                        Vehicule vehicule = new Vehicule(id, marque, modele, puissance, transmission, pays, annee, image, typeVehicule);
                        vehicules.add(vehicule);
                    }
                }

                return vehicules;
            }
            catch (SQLException e)
            {
                lastException = e;
            }
        }

        if (lastException != null)
        {
            throw lastException;
        }

        return vehicules;
    }


    public static void main(String[] args) throws SQLException {
        Requetes requetes = Requetes.getInstance();
        requetes.RecupererVehiculesParType("Voiture");
    }

}
