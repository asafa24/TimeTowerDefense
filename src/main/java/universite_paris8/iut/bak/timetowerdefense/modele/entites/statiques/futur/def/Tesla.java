package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.futur.atk.projectile.ProjectileTesla;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileCercle;

public class Tesla extends TourCercle {
    public static IntegerProperty cout = new SimpleIntegerProperty(180);

    public Tesla(double x, double y) {
        super(cout.get(), x, y, 15, 120, 45, 100);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int rayonExplosion) {
        return new ProjectileTesla(x, y, cible, degats, rayonExplosion) {};
    }

    @Override
    public void inflation() {
        setCout((int) (getCout() * 1.2));
    }
    public static IntegerProperty getCoutPropertyTesla() {
        return cout;
    }
}