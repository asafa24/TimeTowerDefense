package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.projectile.BalleSniper;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourRectangle;

import java.util.List;

public class Sniper extends TourRectangle {

    public static IntegerProperty cout = new SimpleIntegerProperty(175);
    public Sniper(int x, int y) {
        super(cout.get(), x, y, 200, 400, 300, 64);
    }


    @Override
    public void inflation() {
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int largeur) {
        return new BalleSniper(x, y, cible, degats, largeur);
    }

    public static IntegerProperty coutSniper() {
        return cout;
    }
}
