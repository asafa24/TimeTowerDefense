package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.projectiles.Buche;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourRectangle;

public class LanceBuche extends TourRectangle {
    private static final int PRIX_BASE = 80 ;

    public LanceBuche(double x, double y) {
        super(PRIX_BASE, x, y, 25, 360, 150, 64);
    }
    @Override
    public Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int largeur) {
        return new Buche(x, y, cible, degats, largeur);
    }

    public static int getPrixBase() {
        return PRIX_BASE;
    }

}
