package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.projectiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileRectangle;

public class Buche extends ProjectileRectangle {
    public Buche(double x, double y, Ennemi cible, int degats, int largeur) {
        super(x, y, cible, degats, largeur);
    }
}
