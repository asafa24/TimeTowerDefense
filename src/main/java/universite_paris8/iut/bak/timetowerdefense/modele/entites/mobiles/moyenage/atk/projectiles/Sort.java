package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.projectiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.effets.EffetBrulure;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.effets.EffetStun;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effets;

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
                e.ajouterEffet(new EffetStun());
                e.ajouterEffet(new EffetBrulure());
                e.recevoirDegats(getDegats());
            }
        }
    }

    private static ArrayList<Effets> initialiserEffets() {
        ArrayList<Effets> effetsDuProjectile = new ArrayList<>();
        effetsDuProjectile.add(new EffetStun());
        effetsDuProjectile.add(new EffetBrulure());
        return effetsDuProjectile;
    }

}
