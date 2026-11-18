package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.projectiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.effets.EffetBrulure;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.effets.EffetSlow;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effets;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileAEffet;

import java.util.ArrayList;

public class FlechetteEmpoisonne extends ProjectileAEffet {

    public FlechetteEmpoisonne(double x, double y, Ennemi cible, int degats, int dureeEffet) {
        super(x, y, cible, degats, dureeEffet, initialiserEffets());
    }


    private static ArrayList<Effets> initialiserEffets() {
        ArrayList<Effets> effetsDuProjectile = new ArrayList<>();
        effetsDuProjectile.add(new EffetBrulure());
        effetsDuProjectile.add(new EffetSlow());
        return effetsDuProjectile;
    }
}