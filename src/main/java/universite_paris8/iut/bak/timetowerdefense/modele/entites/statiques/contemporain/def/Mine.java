package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;

import java.util.List;

public class Mine extends Piege {
    public static IntegerProperty cout = new SimpleIntegerProperty(30);


    private int degats;
    private double rayonExplosion;
    private boolean aExplose;

    public Mine(double x, double y) {
        super(cout.get(), x, y, 1);
        this.degats = 200;
        this.rayonExplosion = 196;
        this.aExplose = false;
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

    @Override
    public void inflation() {
        this.setCout((int) (this.getCout() * 1.5));
    }

    public boolean aExplose() {
        return aExplose;
    }
    public static IntegerProperty coutMine() {
        return cout;
    }

    public double getRayonExplosion() {
        return rayonExplosion;
    }

}