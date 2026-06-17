package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.projectile.BalleSniper;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourNormal;

public class SoldatBleu extends TourNormal {

    public static IntegerProperty cout = new SimpleIntegerProperty(60);

    public SoldatBleu(double x, double y) {
        super(cout.get(), x, y, 10, 150, 20);
    }
    
    @Override
    public void inflation() {
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible) {
        return new BalleSniper(x, y, cible, this.getDegats(), 2);
    }
    
    public static IntegerProperty coutSoldatBleu() {
        return cout;
    }

}
