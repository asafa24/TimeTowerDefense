package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Fleche;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourNormal;

public class Archer extends TourNormal {
    public static IntegerProperty cout = new SimpleIntegerProperty(80);


    public Archer(double x, double y) {
        super(cout.get(), x, y, 15, 128, 30);
    }

    @Override
    public void inflation(){
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible) {
        return new Fleche(x,y,cible,this.getDegats());
    }
    public static IntegerProperty coutPropertyArcher() {
        return cout;
    }

}
