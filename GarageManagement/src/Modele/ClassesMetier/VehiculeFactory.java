package Modele.ClassesMetier;

public class VehiculeFactory
{
    public static Vehicule creerVehicule(int id, String type, String marque, String modele, String puissance,
                                         String transmission, String pays, int annee, String image)
    {
        return switch (type)
        {
            case "Voiture" -> new Voiture(id, marque, modele, puissance, transmission, pays, annee, image);
            case "Moto" -> new Moto(id, marque, modele, puissance, transmission, pays, annee, image);
            case "Camionnette" -> new Camionnette(id, marque, modele, puissance, transmission, pays, annee, image);
            case "Camion" -> new Camion(id, marque, modele, puissance, transmission, pays, annee, image);
            default -> {
                System.out.println("Type de véhicule inconnu : " + type);
                yield null;
            }
        };
    }

    public static Vehicule creerVehicule(String type, String marque, String modele, String puissance,
                                         String transmission, String pays, int annee, String image)
    {
        return switch (type)
        {
            case "Voiture" -> new Voiture(marque, modele, puissance, transmission, pays, annee, image);
            case "Moto" -> new Moto(marque, modele, puissance, transmission, pays, annee, image);
            case "Camionnette" -> new Camionnette(marque, modele, puissance, transmission, pays, annee, image);
            case "Camion" -> new Camion(marque, modele, puissance, transmission, pays, annee, image);
            default -> {
                System.out.println("Type de véhicule inconnu : " + type);
                yield null;
            }
        };
    }
}
