package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.projectile.BalleSniper;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourNormal;

public class SoldatBleu extends TourNormal {
    private static final int PRIX_BASE = 60;

    public SoldatBleu(double x, double y) {
        super(PRIX_BASE, x, y, 10, 150, 20);
    }

    public static int getPrixBase() {
        return prixActuel(SoldatBleu.class, PRIX_BASE);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible) {
        return new BalleSniper(x, y, cible, this.getDegats(), 2);
    }
}