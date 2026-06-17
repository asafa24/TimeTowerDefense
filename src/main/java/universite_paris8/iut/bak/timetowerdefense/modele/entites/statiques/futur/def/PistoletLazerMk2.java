package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.futur.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.futur.atk.projectile.ProjectileLazerMK2;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourRectangle;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileRectangle;

public class PistoletLazerMk2 extends TourRectangle {
    public static IntegerProperty cout = new SimpleIntegerProperty(200);


    public PistoletLazerMk2(double x, double y) {
        super(cout.get(), x, y, 50, 200, 90, 40);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int largeur) {
        return new ProjectileLazerMK2(x, y, cible, degats, largeur) {};
    }

    @Override
    public void inflation() {
        setCout((int) (getCout() * 1.2));
    }

    public static IntegerProperty getCoutPropertymk2() {
        return cout;
    }
}