package Vue.MéthodesGarageWindow;

import Vue.InterfacesGraphiques.GarageWindow;

import javax.swing.*;

public class ChoixUtilisateur
{
    private ModeTravail modeTravail;
    private StrategieModeTravail strategieModeTravail;

    private static ChoixUtilisateur instance;

    private ChoixUtilisateur()
    {

    }

    public static ChoixUtilisateur getInstance()
    {
        if (instance == null)
        {
            instance = new ChoixUtilisateur();
        }
        return instance;
    }

    public ModeTravail getModeTravail()
    {
        return modeTravail;
    }

    public void ResetModeTravail()
    {
        modeTravail = null;
    }
    public void ChoixMode()
    {
        if (modeTravail == null)
        {
            JComboBox<ModeTravail> comboBoxAjout = new JComboBox<>(ModeTravail.values());
            comboBoxAjout.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, comboBoxAjout, "Choisissez une option pour le choix d'enregistrement", JOptionPane.QUESTION_MESSAGE);
            modeTravail = (ModeTravail) comboBoxAjout.getSelectedItem();
            setModeStrategy(modeTravail);
            System.out.println("Mode choisi : " + modeTravail);
        }
        else
        {
            System.out.println("Mode déjà sélectionné : " + modeTravail);
        }
    }

    private void setModeStrategy(ModeTravail modeTravail)
    {
        switch (modeTravail)
        {
            case FICHIER_TEXTE:
                strategieModeTravail = new StrategieTravailFichierTexte();
                GarageWindow.getGarageWindow().getChanger().setText("Fichier Texte");
                break;
            case BASE_DE_DONNEES:
                strategieModeTravail = new StrategieTravailBaseDeDonnees();
                GarageWindow.getGarageWindow().getChanger().setText("Base De Données");
                break;
            default:
                throw new IllegalArgumentException("Mode de travail non supporté : " + modeTravail);
        }
    }

    public void ChoixAjout()
    {
        if (strategieModeTravail != null)
        {
            strategieModeTravail.Ajouter();
        }
        else
        {
            afficherErreur();
        }
    }

    public void ChoixSuppression()
    {
        if (strategieModeTravail != null)
        {
            strategieModeTravail.Supprimer();
        }
        else
        {
            afficherErreur();
        }
    }

    public void ChoixModification()
    {
        if (strategieModeTravail != null)
        {
            strategieModeTravail.Modifier();
        }
        else
        {
            afficherErreur();
        }
    }

    public void ChoixAffichage()
    {
        if (strategieModeTravail != null)
        {
            strategieModeTravail.Afficher();
        }
        else
        {
            afficherErreur();
        }
    }

    public void ChoixVision()
    {
        if (strategieModeTravail != null)
        {
            strategieModeTravail.Visionner();
        }
        else
        {
            afficherErreur();
        }
    }

    public void ChoixTri()
    {
        if (strategieModeTravail != null)
        {
            strategieModeTravail.Trier();
        }
        else
        {
            afficherErreur();
        }
    }

    private void afficherErreur()
    {
        GarageWindow.getGarageWindow().showMessage("Mode de travail non défini ou invalide !");
    }

}
