package Vue.MéthodesGarageWindow;

import Vue.InterfacesGraphiques.GarageWindow;

public class StrategieTravailBaseDeDonnees implements StrategieModeTravail
{
    @Override
    public void Ajouter()
    {
        MéthodesBoutonsGarageWindowBD.getInstance().BoutonAjouterBD();
    }

    @Override
    public void Modifier()
    {
        MéthodesBoutonsGarageWindowBD.getInstance().BoutonModifierBD();
    }

    @Override
    public void Supprimer()
    {
        MéthodesBoutonsGarageWindowBD.getInstance().BoutonSupprimerBD();
    }

    @Override
    public void Afficher()
    {
        MéthodesGarageWindow.getInstance().RechargerTableBD("Tout");
    }

    @Override
    public void Visionner()
    {
        MéthodesBoutonsGarageWindowBD.getInstance().BoutonVoirBD();
    }

    @Override
    public void Trier()
    {
        MéthodesBoutonsGarageWindowBD.getInstance().BoutonTrierBD();
    }
}
