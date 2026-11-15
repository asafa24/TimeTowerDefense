package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.projectile;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileCercle;

public class ProjectileTesla extends ProjectileCercle {

    public ProjectileTesla(double x, double y, Ennemi cible, int degats, int rayonExplosion) {
        super(x, y, cible, degats, rayonExplosion);
    }
}