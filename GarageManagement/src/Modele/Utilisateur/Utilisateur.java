package Modele.Utilisateur;

public class Utilisateur
{
    private final String nomUtilisateur;
    private final String motDePasse;

    private final String salt;


    public String getMotDePasse()
    {
        return motDePasse;
    }


    public String getSalt()
    {
        return salt;
    }

    public Utilisateur(String nomUtilisateur, String motDePasse, String salt)
    {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.salt = salt;
    }

    @Override
    public String toString()
    {
        return "Utilisateur{" +
                "nomUtilisateur='" + nomUtilisateur + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }

}
