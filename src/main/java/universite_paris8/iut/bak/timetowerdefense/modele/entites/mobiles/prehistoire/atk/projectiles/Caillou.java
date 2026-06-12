package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileCercle;

public class Caillou extends ProjectileCercle {
    public Caillou(double x, double y, Ennemi cible, int degats, int rayonExplosion) {
        super(x, y, cible, degats, rayonExplosion);
    }
}
