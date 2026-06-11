package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.Projectile.Jar;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;

public class CatapulteJAR extends TourCercle {
    public static IntegerProperty cout = new SimpleIntegerProperty(130);
    public CatapulteJAR(double x, double y) {
        super(cout.get(), x, y, 8, 150, 200, 140);
    }

    @Override
    public void inflation(){
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int dureeEffet) {
        return new Jar(x,y,cible,degats,dureeEffet);
    }

    public static IntegerProperty coutPropertyCatapulteJar() {
        return cout;
    }

}
