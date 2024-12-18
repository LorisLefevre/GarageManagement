package Vue.MethodesGarageWindow;

public class StrategieTravailBaseDeDonnees implements StrategieModeTravail
{
    @Override
    public void Ajouter()
    {
        MethodesBoutonsGarageWindowBD.getInstance().BoutonAjouterBD();
    }

    @Override
    public void Modifier()
    {
        MethodesBoutonsGarageWindowBD.getInstance().BoutonModifierBD();
    }

    @Override
    public void Supprimer()
    {
        MethodesBoutonsGarageWindowBD.getInstance().BoutonSupprimerBD();
    }

    @Override
    public void Afficher()
    {
        MethodesGarageWindow.getInstance().RechargerTableBD("Tout");
    }

    @Override
    public void Visionner()
    {
        MethodesBoutonsGarageWindowBD.getInstance().BoutonVoirBD();
    }

    @Override
    public void Trier()
    {
        MethodesBoutonsGarageWindowBD.getInstance().BoutonTrierBD();
    }
}
