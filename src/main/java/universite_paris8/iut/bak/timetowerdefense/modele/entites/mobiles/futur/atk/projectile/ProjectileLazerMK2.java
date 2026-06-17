package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.futur.atk.projectile;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileRectangle;

public class ProjectileLazerMK2 extends ProjectileRectangle {

    public ProjectileLazerMK2(double x, double y, Ennemi cible, int degats, int largeur) {
        super(x, y, cible, degats, largeur);
    }
}