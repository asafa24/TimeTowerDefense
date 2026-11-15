package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.projectile.Rocket;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;

public class LanceRocket extends TourCercle {
    private static final int PRIX_BASE = 140;

    public LanceRocket(double x, double y) {
        super(PRIX_BASE, x, y, 70, 256, 180, 128);
    }

    public static int getPrixBase() {
        return PRIX_BASE;
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int rayonExplosion) {
        return new Rocket(x, y, cible, degats, rayonExplosion);
    }
}