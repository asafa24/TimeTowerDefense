package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourRectangle;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.projectile.ProjectileTemps;

public class TourDuTemps extends TourRectangle {
    private static final int PRIX_BASE = 280;

    public TourDuTemps(double x, double y) {
        super(PRIX_BASE, x, y, 5, 180, 100, 60);
    }

    public static int getPrixBase() {
        return PRIX_BASE;
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int largeur) {
        return new ProjectileTemps(x, y, cible, degats, largeur);
    }
}