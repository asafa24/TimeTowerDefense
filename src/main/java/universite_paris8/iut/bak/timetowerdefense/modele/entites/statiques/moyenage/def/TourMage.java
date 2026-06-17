package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.projectiles.Sort;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourStun;

public class TourMage extends TourStun {
    public static IntegerProperty cout = new SimpleIntegerProperty(130);

    public TourMage(double x, double y) {
        super(cout.get(), x, y, 15, 150, 150, 50);
    }
    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int dureeStun) {
        return new Sort(x, y, cible, degats, dureeStun);
    }

    @Override
    public void inflation() {
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }
    public static IntegerProperty coutPropertyPorteSable() {
        return cout;
    }

}
