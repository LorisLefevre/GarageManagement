package Modèle.ClassesMetier;

public class VehiculeFactory
{
    public static Vehicule creerVehicule(int id, String type, String marque, String modele, String puissance,
                                         String transmission, String pays, int annee, String image)
    {
        switch (type)
        {
            case "Voiture":
                return new Voiture(id, marque, modele, puissance, transmission, pays, annee, image);
            case "Moto":
                return new Moto(id, marque, modele, puissance, transmission, pays, annee, image);
            case "Camionnette":
                return new Camionnette(id, marque, modele, puissance, transmission, pays, annee, image);
            case "Camion":
                return new Camion(id, marque, modele, puissance, transmission, pays, annee, image);
            default:
                System.out.println("Type de véhicule inconnu : " + type);
                return null;
        }
    }

    public static Vehicule creerVehicule(String type, String marque, String modele, String puissance,
                                         String transmission, String pays, int annee, String image)
    {
        switch (type)
        {
            case "Voiture":
                return new Voiture(marque, modele, puissance, transmission, pays, annee, image);
            case "Moto":
                return new Moto(marque, modele, puissance, transmission, pays, annee, image);
            case "Camionnette":
                return new Camionnette(marque, modele, puissance, transmission, pays, annee, image);
            case "Camion":
                return new Camion(marque, modele, puissance, transmission, pays, annee, image);
            default:
                System.out.println("Type de véhicule inconnu : " + type);
                return null;
        }
    }
}
