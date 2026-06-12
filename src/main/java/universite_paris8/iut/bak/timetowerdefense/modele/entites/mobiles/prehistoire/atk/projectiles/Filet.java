package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileAEffet;

import java.util.ArrayList;

public class Filet extends ProjectileAEffet {
    public Filet(double x, double y, Ennemi cible, int degats, int dureeEffet) {
        super(x, y, cible, degats, dureeEffet, initialiserEffets());
    }


    private static ArrayList<Effet> initialiserEffets() {
        ArrayList<Effet> effetsDuProjectile = new ArrayList<>();
        effetsDuProjectile.add(Effet.STUN);
        return effetsDuProjectile;
    }
}
