package Vue.MéthodesGarageWindow;

public class StrategieTravailFichierTexte implements StrategieModeTravail
{
    @Override
    public void Ajouter()
    {
        MéthodesBoutonsGarageWindow.getInstance().BoutonAjouter();
    }

    @Override
    public void Modifier()
    {
        MéthodesBoutonsGarageWindow.getInstance().BoutonModifier();
    }

    @Override
    public void Supprimer()
    {
        MéthodesBoutonsGarageWindow.getInstance().BoutonSupprimer();
    }

    @Override
    public void Afficher()
    {
        MéthodesGarageWindow.getInstance().RechargerTable();
    }

    @Override
    public void Visionner()
    {
        MéthodesBoutonsGarageWindow.getInstance().BoutonVoir();
    }

    @Override
    public void Trier()
    {
        MéthodesBoutonsGarageWindow.getInstance().BoutonTrier();
    }

}
