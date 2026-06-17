package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Fleche;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourNormal;


public class ArbreRuste extends TourNormal {

    public static IntegerProperty cout = new SimpleIntegerProperty(40);
    public ArbreRuste(double x, double y) {
        super(cout.get(), x, y, 10, 64, 60);
    }

    @Override
    public void inflation(){
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }
    public static IntegerProperty coutPropertyArbre() {
        return cout;
    }
    protected Projectile creerProjectile(double x, double y, Ennemi cible ) {
        return new Fleche(x,y,cible,this.getDegats());
    }

}
