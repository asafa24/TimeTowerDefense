package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Caillou;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;

public class CatapulteCaillou extends TourCercle {
    public static IntegerProperty cout = new SimpleIntegerProperty(130);
    public CatapulteCaillou(double x, double y) {
        super(cout.get(), x, y, 40, 128, 200, 96);
    }

    @Override
    public void inflation(){
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int rayonExplosion) {
        return new Caillou(x,y,cible,degats,rayonExplosion);
    }

    public static IntegerProperty coutPropertyArbre() {
        return cout;
    }
}
