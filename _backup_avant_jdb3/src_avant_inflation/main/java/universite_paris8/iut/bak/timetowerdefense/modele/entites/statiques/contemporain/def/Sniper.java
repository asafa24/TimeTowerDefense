package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.projectile.BalleSniper;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourRectangle;

public class Sniper extends TourRectangle {
    private static final int PRIX_BASE = 175;

    public Sniper(int x, int y) {
        super(PRIX_BASE, x, y, 200, 400, 300, 64);
    }

    public static int getPrixBase() {
        return PRIX_BASE;
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int largeur) {
        return new BalleSniper(x, y, cible, degats, largeur);
    }
}