package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.projectiles.FlechetteEmpoisonne;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourPoison;

public class TotemFlechette extends TourPoison {
    private static final int PRIX_BASE = 50;

    public TotemFlechette(double x, double y) {
        super(PRIX_BASE, x, y, 10, 128, 30, 60);
    }

    public static int getPrixBase() {
        return PRIX_BASE;
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int dureePoison) {
        return new FlechetteEmpoisonne(x, y, cible, degats, dureePoison);
    }
}