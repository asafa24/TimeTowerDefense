package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Rocket;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;

public class LanceRocket extends TourCercle {
    public static IntegerProperty cout = new SimpleIntegerProperty(140);


    public LanceRocket(double x, double y) {
        super(cout.get(), x, y, 60, 3, 60, 128);
    }

    @Override
    public void inflation() {
        this.setCout((int) (this.getCout() * 1.5));
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int rayonExplosion) {
        return new Rocket(x, y, cible, degats, rayonExplosion);
    }
    public static IntegerProperty coutRocket() {
        return cout;
    }

}