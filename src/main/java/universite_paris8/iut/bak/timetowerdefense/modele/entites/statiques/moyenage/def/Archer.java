package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Fleche;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourNormal;

public class Archer extends TourNormal {

    public Archer(double x, double y) {
        super(50, x, y, 15, 128, 30);
    }

    @Override
    public void inflation() {
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible) {
        return new Fleche(x,y,cible,this.getDegats());
    }
}
