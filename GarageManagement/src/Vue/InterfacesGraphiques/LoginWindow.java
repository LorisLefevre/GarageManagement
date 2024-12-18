package Vue.InterfacesGraphiques;

import Controleur.ActionsControleur;
import Controleur.Controleur;
import Modele.Utilisateur.GestionUtilisateurs;
import Modele.Utilisateur.Utilisateur;
import Vue.MethodesGarageWindow.ChoixUtilisateur;
import Vue.Vues.VueLoginWindow;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements VueLoginWindow
{
    private LoginWindow loginWindow;
    private GarageWindow garageWindow;

    private final JTextField UsernameField;
    public JTextField getUsernameField()
    {
        return UsernameField;
    }

    private final JPasswordField PasswordField;
    public JPasswordField getPasswordField()
    {
        return PasswordField;
    }

    private final JButton LoginButton;

    private final JButton LogoutButton;

    private static LoginWindow instance;

    public  LoginWindow getLoginWindow()
    {
        if (instance == null)
        {
            instance = new LoginWindow();
        }
        return instance;
    }

    public LoginWindow()
    {
        super("Connexion...");
        setSize(350,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        UsernameField = new JTextField(20);
        PasswordField = new JPasswordField(20);
        LoginButton = new JButton("Se connecter");
        LogoutButton = new JButton("Se déconnecter");

        mainPanel.add(new JLabel("Nom d'utilisateur:"));
        mainPanel.add(UsernameField);
        mainPanel.add(new JLabel("Mot de passe:"));
        mainPanel.add(PasswordField);
        mainPanel.add(LoginButton);
        mainPanel.add(LogoutButton);

        setContentPane(mainPanel);

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
    public void setControleur(Controleur controleur)
    {
        this.loginWindow = this;
        LoginButton.setActionCommand(ActionsControleur.LOGIN);
        loginWindow.addLoginListener(controleur);

        LogoutButton.setActionCommand(ActionsControleur.LOGOUT);
        loginWindow.addLogoutListener(controleur);
    }

    @Override
    public void Login()
    {
        System.out.println("Connexion de l'utilisateur...");
        String Username = loginWindow.getUsername();
        String Password = loginWindow.getPassword();
        Utilisateur utilisateur = GestionUtilisateurs.seConnecter(Username, Password);

        if (utilisateur != null)
        {
            System.out.println("Connexion réussie !");

            if (this.garageWindow == null)
            {
                System.out.println("Création d'une nouvelle fenêtre GarageWindow...");
                this.garageWindow = GarageWindow.getGarageWindow();
            }

            ChoixUtilisateur.getInstance().ChoixMode();
            garageWindow.ChargementDonnees();
            garageWindow.setUserLabel(Username);
            garageWindow.setVisible(true);
        }
        else
        {
            loginWindow.showMessage("Login ou mot de passe incorrect !");
        }

    }


    @Override
    public void Logout()
    {
        loginWindow.getLoginWindow().getUsernameField().setText("");
        loginWindow.getLoginWindow().getPasswordField().setText("");

        if(this.garageWindow != null)
        {
            this.garageWindow.dispose();
            GarageWindow.resetGarageWindow();
            garageWindow = null;

            VehiculeInformationWindow vehiculeInformationWindow = VehiculeInformationWindow.getInstance();
            vehiculeInformationWindow.setVisible(false);
            vehiculeInformationWindow.dispose();
            VehiculeInformationWindow.resetInstance();

        }
    }
}
