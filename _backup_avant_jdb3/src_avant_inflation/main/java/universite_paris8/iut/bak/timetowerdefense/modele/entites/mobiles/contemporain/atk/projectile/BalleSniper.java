package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.projectile;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileRectangle;

public class BalleSniper extends ProjectileRectangle {

    public BalleSniper(double x, double y, Ennemi cible, int degats, int largeur) {
        super(x, y, cible, degats, largeur);
        setVitesse(10);
    }
}
