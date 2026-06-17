package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourRectangle;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.projectiles.ProjectileTemps;

public class TourDuTemps extends TourRectangle {
    public static IntegerProperty cout = new SimpleIntegerProperty(280);


    public TourDuTemps(double x, double y) {

        super(cout.get(), x, y, 5, 180, 100, 60);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int largeur) {
        return new ProjectileTemps(x, y, cible, degats, largeur);
    }

    @Override
    public void inflation() {
        setCout((int) (getCout() * 1.2));
    }
    public static IntegerProperty getCoutPropertyTourTemp() {
        return cout;
    }
}