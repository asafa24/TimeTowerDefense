package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;

import java.util.List;

public class Mine extends Piege {
    private static final int PRIX_BASE = 30;

    private int degats;
    private double rayonExplosion;
    private boolean aExplose;

    public Mine(double x, double y) {
        super(PRIX_BASE, x, y, 1);
        this.degats = 200;
        this.rayonExplosion = 196;
        this.aExplose = false;
    }

    public static int getPrixBase() {
        return prixActuel(Mine.class, PRIX_BASE);
    }

    @Override
    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles) {
        if (aExplose || estMort()) return;

        for (Ennemi e : ennemis) {
            if (aAtteintPiege(e)) {
                declencherExplosion(ennemis);
                break;
            }
        }
    }

    private void declencherExplosion(List<Ennemi> ennemis) {
        this.aExplose = true;

        double centreMineX = (getX() * 64) + 32;
        double centreMineY = (getY() * 64) + 32;

        for (Ennemi e : ennemis) {
            double distance = Math.hypot(centreMineX - e.getCentreX(), centreMineY - e.getCentreY());
            if (distance <= rayonExplosion) {
                e.recevoirDegats(degats);
            }
        }
        this.recevoirDegats(this.getPv());
    }

    public boolean aExplose() {
        return aExplose;
    }

    public double getRayonExplosion() {
        return rayonExplosion;
    }
}