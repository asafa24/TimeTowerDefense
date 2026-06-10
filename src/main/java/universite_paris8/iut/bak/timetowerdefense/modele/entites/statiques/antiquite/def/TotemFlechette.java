package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.Projectile.FlechetteEmpoisonne;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourPoison;

public class TotemFlechette extends TourPoison {
    public static IntegerProperty cout = new SimpleIntegerProperty(50);

    public TotemFlechette(double x, double y) {
        super(cout.get(), x, y, 10, 128, 30, 60);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int dureePoison) {
        return new FlechetteEmpoisonne(x,y,cible,degats,dureePoison);
    }

    @Override
    public void inflation() {
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    public static IntegerProperty coutPropertyFlechette() {
        return cout;
    }
}