package Modèle.Utilisateur;

public class Utilisateur
{
    private String nomUtilisateur;
    private String motDePasse;

    private String salt;

    public String getNomUtilisateur()
    {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur)
    {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse()
    {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse)
    {
        this.motDePasse = motDePasse;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public Utilisateur()
    {
        nomUtilisateur = "admin";
        motDePasse = "abcd";
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

    public void Afficher()
    {
        System.out.println("Nom d'utilisateur : " + nomUtilisateur);
        System.out.println("Mot de passe : " + motDePasse);
    }




}
