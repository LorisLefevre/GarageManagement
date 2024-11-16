package Vue.MéthodesGarageWindow;

import javax.swing.*;

public class ChoixUtilisateur
{
    private static ChoixUtilisateur instance;

    public static ChoixUtilisateur getInstance()
    {
        if(instance == null)
        {
            instance = new ChoixUtilisateur();
        }

        return instance;
    }

    public void ChoixAjout()
    {
        JComboBox<String> comboBoxAjout = new JComboBox<>(new String[]{"Fichier.txt", "Base de données"});
        comboBoxAjout.setSelectedIndex(0); // Option par défaut
        JOptionPane.showMessageDialog(null, comboBoxAjout, "Choisissez une option pour Ajout", JOptionPane.QUESTION_MESSAGE);
        String choix = (String) comboBoxAjout.getSelectedItem();
        System.out.println("Choix pour Ajout : " + choix);

        if ("Fichier.txt".equals(choix))
        {
            System.out.println("Ajout dans le fichier texte sélectionné.");
            MéthodesBoutonsGarageWindow.getInstance().BoutonAjouter();
        }
        else if ("Base de données".equals(choix))
        {
            System.out.println("Ajout dans la base de données sélectionné.");
            MéthodesBoutonsGarageWindowBD.getInstance().BoutonAjouterBD();
        }
        else
        {
            System.out.println("Aucun choix valide.");
        }
    }

    public void ChoixSuppression()
    {
        JComboBox<String> comboBoxSuppression = new JComboBox<>(new String[]{"Fichier.txt", "Base de données"});
        comboBoxSuppression.setSelectedIndex(0); // Option par défaut
        JOptionPane.showMessageDialog(null, comboBoxSuppression, "Choisissez une option pour Suppression", JOptionPane.QUESTION_MESSAGE);
        String choix = (String) comboBoxSuppression.getSelectedItem();
        System.out.println("Choix pour Suppression : " + choix);

        if ("Fichier.txt".equals(choix))
        {
            System.out.println("Suppression dans le fichier texte sélectionné.");
            MéthodesBoutonsGarageWindow.getInstance().BoutonSupprimer();
        }
        else if ("Base de données".equals(choix))
        {
            System.out.println("Suppression dans la base de données sélectionné.");
            MéthodesBoutonsGarageWindowBD.getInstance().BoutonSupprimerBD();
        }
        else
        {
            System.out.println("Aucun choix valide.");
        }
    }

    public void ChoixModification()
    {
        JComboBox<String> comboBoxModification = new JComboBox<>(new String[]{"Fichier.txt", "Base de données"});
        comboBoxModification.setSelectedIndex(0); // Option par défaut
        JOptionPane.showMessageDialog(null, comboBoxModification, "Choisissez une option pour Modification", JOptionPane.QUESTION_MESSAGE);
        String choix = (String) comboBoxModification.getSelectedItem();
        System.out.println("Choix pour Modification : " + choix);

        if ("Fichier.txt".equals(choix))
        {
            System.out.println("Modification dans le fichier texte sélectionné.");
            MéthodesBoutonsGarageWindow.getInstance().BoutonModifier();
        }
        else if ("Base de données".equals(choix))
        {
            System.out.println("Modification dans la base de données sélectionné.");
            MéthodesBoutonsGarageWindowBD.getInstance().BoutonModifierBD();
        }
        else
        {
            System.out.println("Aucun choix valide.");
        }
    }

}
