package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.effets.EffetStun;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effets;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileAEffet;

import java.util.ArrayList;

public class Filet extends ProjectileAEffet {
    public Filet(double x, double y, Ennemi cible, int degats, int dureeEffet) {
        super(x, y, cible, degats, dureeEffet, initialiserEffets());
    }


    private static ArrayList<Effets> initialiserEffets() {
        ArrayList<Effets> effetsDuProjectile = new ArrayList<>();
        effetsDuProjectile.add(new EffetStun());
        return effetsDuProjectile;
    }
}
