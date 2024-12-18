package Modele.ClassesMetier;

public class Moto extends Vehicule
{
    public Moto(String marque, String modele, String puissance, String transmission, String pays, int annee, String image)
    {
        super(marque, modele, puissance, transmission, pays, annee, image, "Moto");
    }

    public Moto(int id, String marque, String modele, String puissance, String transmission, String pays, int annee, String image)
    {
        super(id, marque, modele, puissance, transmission, pays, annee, image, "Moto");
    }

}
