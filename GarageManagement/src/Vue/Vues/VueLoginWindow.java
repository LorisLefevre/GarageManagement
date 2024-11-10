package Vue.Vues;

import Contrôleur.Controleur;
import Modèle.Utilisateur.GestionUtilisateurs;

public interface VueLoginWindow
{
    void run();
    void setContrôleur(Controleur contrôleur);
    GestionUtilisateurs Login();

    void Logout();
}
