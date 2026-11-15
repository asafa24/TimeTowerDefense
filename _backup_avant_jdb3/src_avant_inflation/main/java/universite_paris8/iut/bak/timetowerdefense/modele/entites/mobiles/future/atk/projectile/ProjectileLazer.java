package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.projectile;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

public class ProjectileLazer extends Projectile {

    public ProjectileLazer(double x, double y, Ennemi cible, int degats) {
        super(x, y, cible, degats);
    }
}