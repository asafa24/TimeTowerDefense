package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.projectiles.Buche;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourRectangle;

public class LanceBuche extends TourRectangle {
    public static IntegerProperty cout = new SimpleIntegerProperty(80);

    public LanceBuche(double x, double y) {
        super(150, x, y, 25, 360, 150, 64);
    }


    @Override
    public void inflation() {
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    @Override
    public Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int largeur) {
        return new Buche(x, y, cible, degats, largeur);
    }

    public static IntegerProperty coutPropertyLanceBiuche() {
        return cout;
    }

}
