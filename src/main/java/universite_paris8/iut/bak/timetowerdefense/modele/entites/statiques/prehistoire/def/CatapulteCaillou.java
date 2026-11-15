package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Caillou;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;

public class CatapulteCaillou extends TourCercle {
    private static final int PRIX_BASE = 130;

    public CatapulteCaillou(double x, double y) {
        super(PRIX_BASE, x, y, 40, 128, 180, 96);
    }

    public static int getPrixBase() {
        return prixActuel(CatapulteCaillou.class, PRIX_BASE);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int rayonExplosion) {
        return new Caillou(x, y, cible, degats, rayonExplosion);
    }
}