package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.projectiles.Jar;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;

public class CatapulteJAR extends TourCercle {
    private static final int PRIX_BASE = 130;

    public CatapulteJAR(double x, double y) {
        super(PRIX_BASE, x, y, 8, 150, 200, 140);
    }

    public static int getPrixBase() {
        return prixActuel(CatapulteJAR.class, PRIX_BASE);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int dureeEffet) {
        return new Jar(x, y, cible, degats, dureeEffet);
    }
}