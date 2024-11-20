package Vue.MéthodesGarageWindow;

import Vue.InterfacesGraphiques.GarageWindow;

import javax.swing.*;

public class ChoixUtilisateur
{
    private String modeTravail;

    public String getModeTravail()
    {
        return modeTravail;
    }
    private static ChoixUtilisateur instance;

    public static ChoixUtilisateur getInstance()
    {
        if(instance == null)
        {
            instance = new ChoixUtilisateur();
        }

        return instance;
    }

    public void ChoixMode()
    {
        if (modeTravail == null)
        {
            JComboBox<String> comboBoxAjout = new JComboBox<>(new String[]{"Fichier.txt", "Base de données"});
            comboBoxAjout.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, comboBoxAjout, "Choisissez une option pour le choix d'enregistrement", JOptionPane.QUESTION_MESSAGE);
            modeTravail = (String) comboBoxAjout.getSelectedItem();
            System.out.println("Mode choisi : " + modeTravail);
        }
        else
        {
            System.out.println("Mode déjà sélectionné : " + modeTravail);
        }
    }

    public void ChoixAjout()
    {
        String mode = ChoixUtilisateur.getInstance().getModeTravail();
        if(mode.equals("Fichier.txt"))
        {
            MéthodesBoutonsGarageWindow.getInstance().BoutonAjouter();
        }

        else if(mode.equals("Base de données"))
        {
            MéthodesBoutonsGarageWindowBD.getInstance().BoutonAjouterBD();
        }

        else
        {
            GarageWindow.getGarageWindow().showMessage("Désolé, mais nous n'avons trouvé aucun moyen pour ajouter un véhicule");
        }
    }

    public void ChoixSuppression()
    {
        String mode = ChoixUtilisateur.getInstance().getModeTravail();
        if(mode.equals("Fichier.txt"))
        {
            MéthodesBoutonsGarageWindow.getInstance().BoutonSupprimer();
        }

        else if(mode.equals("Base de données"))
        {
            MéthodesBoutonsGarageWindowBD.getInstance().BoutonSupprimerBD();
        }

        else
        {
            GarageWindow.getGarageWindow().showMessage("Désolé, mais nous n'avons trouvé aucun moyen pour supprimer le véhicule");
        }
    }

    public void ChoixModification()
    {
        String mode = ChoixUtilisateur.getInstance().getModeTravail();
        if(mode.equals("Fichier.txt"))
        {
            MéthodesBoutonsGarageWindow.getInstance().BoutonModifier();
        }

        else if(mode.equals("Base de données"))
        {
            MéthodesBoutonsGarageWindowBD.getInstance().BoutonModifierBD();
        }

        else
        {
            GarageWindow.getGarageWindow().showMessage("Désolé, mais nous n'avons trouvé aucun moyen pour modifier le véhicule");
        }
    }

    public void ChoixAffichage()
    {
        String mode = ChoixUtilisateur.getInstance().getModeTravail();
        if(mode.equals("Fichier.txt"))
        {
            MéthodesGarageWindow.getInstance().RechargerTable();
        }

        else if(mode.equals("Base de données"))
        {
            MéthodesGarageWindow.getInstance().RechargerTableBD();
        }

        else
        {
            GarageWindow.getGarageWindow().showMessage("Désolé, mais nous n'avons trouvé aucun moyen pour afficher les véhicules");
        }
    }

    public void ChoixVision()
    {
        String mode = ChoixUtilisateur.getInstance().getModeTravail();
        if(mode.equals("Fichier.txt"))
        {
            MéthodesBoutonsGarageWindow.getInstance().BoutonVoir();
        }

        else if(mode.equals("Base de données"))
        {
            //MéthodesBoutonsGarageWindowBD.getInstance().BoutonVoirBD();
            GarageWindow.getGarageWindow().showMessage("Cette fonctionnalité n'est pas encore disponible. Veuillez réessayer plus tard");
        }

        else
        {
            GarageWindow.getGarageWindow().showMessage("Désolé, mais nous n'avons trouvé aucun moyen pour afficher les véhicules");
        }
    }

}
