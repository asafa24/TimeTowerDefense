package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.projectile.ProjectileTesla;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;

public class Tesla extends TourCercle {
    private static final int PRIX_BASE = 180;

    public Tesla(double x, double y) {
        super(PRIX_BASE, x, y, 15, 120, 45, 100);
    }

    public static int getPrixBase() {
        return prixActuel(Tesla.class, PRIX_BASE);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int rayonExplosion) {
        return new ProjectileTesla(x, y, cible, degats, rayonExplosion) {};
    }
}