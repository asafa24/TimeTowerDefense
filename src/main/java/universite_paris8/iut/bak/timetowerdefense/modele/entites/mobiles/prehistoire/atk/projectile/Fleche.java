package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectile;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

public class Fleche extends Projectile {
    public Fleche(double x, double y, Ennemi cible) {
        super(x, y, cible, 25);
    }
}
