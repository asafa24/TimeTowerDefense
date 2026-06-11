package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.Projectile;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileCercle;

import java.util.List;

public class Jar extends ProjectileCercle {
    public Jar(double x, double y, Ennemi cible, int degats, int dureeEffet) {
        super(x, y, cible, degats, dureeEffet);
    }
    @Override
    public void appliquerImpact(List<Ennemi> ennemis) {

        super.appliquerImpact(ennemis);

        double epicentreX = getX();
        double epicentreY = getY();

        for (Ennemi e : ennemis) {
            double distanceExplosion = Math.hypot(epicentreX - e.getCentreX(), epicentreY - e.getCentreY());

            if (distanceExplosion <= getRayonExplosion()) {
                e.appliqueEffet(Effet.BURN);
            }
        }
    }
}
