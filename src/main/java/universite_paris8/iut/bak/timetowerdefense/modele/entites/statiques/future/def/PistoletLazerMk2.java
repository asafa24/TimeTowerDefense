package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.projectile.ProjectileLazerMK2;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourRectangle;

public class PistoletLazerMk2 extends TourRectangle {
    private static final int PRIX_BASE = 200;

    public PistoletLazerMk2(double x, double y) {
        super(PRIX_BASE, x, y, 50, 200, 90, 40);
    }

    public static int getPrixBase() {
        return prixActuel(PistoletLazerMk2.class, PRIX_BASE);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int largeur) {
        return new ProjectileLazerMK2(x, y, cible, degats, largeur) {};
    }
    @Override
    protected double getTauxInflation() {
        return 1.2;
    }






















}