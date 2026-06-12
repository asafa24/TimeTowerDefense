package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.projectiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileAEffet;

import java.util.ArrayList;
import java.util.List;

public class Sort extends ProjectileAEffet {
    public Sort(double x, double y, Ennemi cible, int degats, int dureeEffet) {
        super(x, y, cible, degats, dureeEffet, initialiserEffets());
    }

    @Override
    public void appliquerImpact(List<Ennemi> ennemis) {
        double epicentreX = getX();
        double epicentreY = getY();
        for (Ennemi e : ennemis){
            double distanceExplosion = Math.hypot(epicentreX - e.getCentreX(), epicentreY - e.getCentreY());
            if (distanceExplosion <= 76){
                e.appliqueEffet(Effet.STUN);
                e.appliqueEffet(Effet.BURN);
                e.recevoirDegats(getDegats());
            }
        }
    }

    private static ArrayList<Effet> initialiserEffets() {
        ArrayList<Effet> effetsDuProjectile = new ArrayList<>();
        effetsDuProjectile.add(Effet.STUN);
        effetsDuProjectile.add(Effet.BURN);
        return effetsDuProjectile;
    }

}
