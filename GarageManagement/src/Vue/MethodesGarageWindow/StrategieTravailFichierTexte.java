package Vue.MethodesGarageWindow;

public class StrategieTravailFichierTexte implements StrategieModeTravail
{
    @Override
    public void Ajouter()
    {
        MethodesBoutonsGarageWindow.getInstance().BoutonAjouter();
    }

    @Override
    public void Modifier()
    {
        MethodesBoutonsGarageWindow.getInstance().BoutonModifier();
    }

    @Override
    public void Supprimer()
    {
        MethodesBoutonsGarageWindow.getInstance().BoutonSupprimer();
    }

    @Override
    public void Afficher()
    {
        MethodesGarageWindow.getInstance().RechargerTable();
    }

    @Override
    public void Visionner()
    {
        MethodesBoutonsGarageWindow.getInstance().BoutonVoir();
    }

    @Override
    public void Trier()
    {
        MethodesBoutonsGarageWindow.getInstance().BoutonTrier();
    }

}
