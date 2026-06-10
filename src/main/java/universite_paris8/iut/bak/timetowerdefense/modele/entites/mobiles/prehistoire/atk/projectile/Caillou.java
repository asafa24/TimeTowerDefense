package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectile;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileCercle;

import java.util.ArrayList;

public class Caillou extends ProjectileCercle {
    public Caillou(double x, double y, Ennemi cible, int degats, int dureeEffet) {
        super(x, y, cible, degats, dureeEffet);
    }
}
