package Modèle.Utilisateur;

public class Utilisateur
{
    private String nomUtilisateur;
    private String motDePasse;

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

    public Utilisateur()
    {
        nomUtilisateur = "admin";
        motDePasse = "abcd";
    }

    public Utilisateur(String nomUtilisateur, String motDePasse)
    {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
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


    public static void main(String[] args)
    {
        System.out.println("\nTest constructeur par défaut\n");
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.Afficher();

        System.out.println("\nTest ToString\n");
        System.out.println(utilisateur.toString());

        System.out.println("\nTest du constructeur d'initalisation\n");
        Utilisateur utilisateur1 = new Utilisateur("Loris", "LRS");
        utilisateur1.Afficher();

        System.out.println("\nTest du constructeur d'initialisation avec des variables injectées\n");
        String nomUtilisateurTest = "Loris Lefevre";
        String motDePasseTest = "LRSR";
        Utilisateur utilisateur2 = new Utilisateur(nomUtilisateurTest, motDePasseTest);
        utilisateur2.Afficher();

        System.out.println("\nTest des setters et getters\n");
        Utilisateur utilisateur3 = new Utilisateur();
        utilisateur3.setNomUtilisateur("Jean Dupont");
        utilisateur3.setMotDePasse("12345");

        System.out.println("Nom d'utilisateur après setter: " + utilisateur3.getNomUtilisateur());
        System.out.println("Mot de passe après setter: " + utilisateur3.getMotDePasse());
        utilisateur3.Afficher();

        utilisateur3.setNomUtilisateur("Alice Martin");
        utilisateur3.setMotDePasse("alice2024");

        System.out.println("Nom d'utilisateur après mise à jour: " + utilisateur3.getNomUtilisateur());
        System.out.println("Mot de passe après mise à jour: " + utilisateur3.getMotDePasse());
        utilisateur3.Afficher();

    }

}
