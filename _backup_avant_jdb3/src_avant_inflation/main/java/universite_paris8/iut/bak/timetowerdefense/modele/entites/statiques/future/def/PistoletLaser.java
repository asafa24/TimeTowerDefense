package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.projectile.ProjectileLazer;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourNormal;

public class PistoletLaser extends TourNormal {
    private static final int PRIX_BASE = 60;

    public PistoletLaser(double x, double y) {
        super(PRIX_BASE, x, y, 35, 150, 60);
    }

    public static int getPrixBase() {
        return PRIX_BASE;
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible) {
        return new ProjectileLazer(x, y, cible, getDegats());
    }

    @Override
    public void inflation() {
        // Inflation spécifique de 20% pour le laser
        setCout((int) (getCout() * 1.2));
    }
}