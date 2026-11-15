package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Fleche;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourNormal;

public class Archer extends TourNormal {
    private static final int PRIX_BASE = 80;

    public Archer(double x, double y) {
        super(PRIX_BASE, x, y, 15, 128, 30);
    }

    public static int getPrixBase() {
        return PRIX_BASE;
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible) {
        return new Fleche(x, y, cible, this.getDegats());
    }
}