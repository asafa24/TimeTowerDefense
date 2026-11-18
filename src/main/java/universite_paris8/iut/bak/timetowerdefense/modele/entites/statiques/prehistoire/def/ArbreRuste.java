package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Fleche;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourNormal;

public class ArbreRuste extends TourNormal {
    private static final int PRIX_BASE = 40;

    public ArbreRuste(double x, double y) {
        super(PRIX_BASE, x, y, 10, 64, 60);
    }

    public static int getPrixBase() {
        return prixActuel(ArbreRuste.class, PRIX_BASE);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible) {
        return new Fleche(x, y, cible, this.getDegats());
    }
}