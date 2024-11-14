package Vue.InterfacesGraphiques;

import Contrôleur.ActionsControleur;
import Contrôleur.Controleur;
import Modèle.Utilisateur.GestionUtilisateurs;
import Modèle.Utilisateur.Utilisateur;
import Vue.Vues.VueLoginWindow;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements VueLoginWindow
{
    private LoginWindow loginWindow;
    private GarageWindow garageWindow;

    private Controleur controleur;

    private JTextField UsernameField;
    public JTextField getUsernameField()
    {
        return UsernameField;
    }

    private JPasswordField PasswordField;
    public JPasswordField getPasswordField()
    {
        return PasswordField;
    }

    private JButton LoginButton;

    private JButton LogoutButton;

    private JPanel MainPanel;

    private static LoginWindow instance;

    public  LoginWindow getLoginWindow()
    {
        if (instance == null)
        {
            instance = new LoginWindow();
        }
        return instance;
    }

    public LoginWindow getThis()
    {
        return this;
    }

    public LoginWindow()
    {
        super("Login...");
        setSize(350,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainPanel = new JPanel();
        UsernameField = new JTextField(20);
        PasswordField = new JPasswordField(20);
        LoginButton = new JButton("Login");
        LogoutButton = new JButton("Logout");

        MainPanel.add(new JLabel("Username:"));
        MainPanel.add(UsernameField);
        MainPanel.add(new JLabel("Password:"));
        MainPanel.add(PasswordField);
        MainPanel.add(LoginButton);
        MainPanel.add(LogoutButton);

        setContentPane(MainPanel);

    }

    public String getUsername()
    {
        return UsernameField.getText();
    }

    public String getPassword()
    {
        return new String(PasswordField.getPassword());
    }

    public void addLoginListener(ActionListener listener)
    {
        LoginButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener)
    {
        LogoutButton.addActionListener(listener);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void run()
    {
        this.loginWindow = this;
        setVisible(true);
    }

    @Override
    public void setContrôleur(Controleur controleur)
    {
        this.loginWindow = this;
        LoginButton.setActionCommand(ActionsControleur.LOGIN);
        loginWindow.addLoginListener(controleur);

        LogoutButton.setActionCommand(ActionsControleur.LOGOUT);
        loginWindow.addLogoutListener(controleur);
    }

    @Override
    public GestionUtilisateurs Login()
    {
        System.out.println("Connexion de l'utilisateur...");
        String Username = loginWindow.getUsername();
        String Password = loginWindow.getPassword();
        Utilisateur utilisateur = GestionUtilisateurs.seConnecter(Username, Password);
        if(utilisateur != null)
        {
            System.out.println("Connexion reussie !");

            if(this.garageWindow == null)
            {

                System.out.println("Nouvelle fenêtre d'accueil...");
                this.garageWindow = new GarageWindow();
            }
            this.garageWindow = GarageWindow.getGarageWindow();
            garageWindow.ChargementDonnees();
            garageWindow.setUserLabel(Username);
            garageWindow.setVisible(true);
        }

        else
        {
            loginWindow.showMessage("Login ou mot de passe incorrect !");
        }

        return null;
    }

    @Override
    public void Logout()
    {
        System.out.println("\nDéconnexion de l'utilisateur\n");
        loginWindow.getLoginWindow().getUsernameField().setText("");
        loginWindow.getLoginWindow().getPasswordField().setText("");

        this.garageWindow = GarageWindow.getGarageWindow();
        garageWindow.setVisible(false);
    }

}
