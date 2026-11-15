package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

import java.util.List;

public abstract class ProjectileCercle extends Projectile {
    private int rayonExplosion;

    public ProjectileCercle(double x, double y, Ennemi cible, int degats, int rayonExplosion){
        super(x, y, cible, degats);
        this.rayonExplosion = rayonExplosion;
    }


    @Override
    public void appliquerImpact(List<Ennemi> ennemis) {
            double epicentreX = getX();
            double epicentreY = getY();
            for (Ennemi e : ennemis){
                double distanceExplosion = Math.hypot(epicentreX - e.getCentreX(), epicentreY - e.getCentreY());
                if (distanceExplosion <= rayonExplosion){
                    e.recevoirDegats(getDegats());
                }
            }
    }

    public int getRayonExplosion() {
        return rayonExplosion;
    }
}
