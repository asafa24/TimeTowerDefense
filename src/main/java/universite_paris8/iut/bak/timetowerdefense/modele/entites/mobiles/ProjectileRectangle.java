package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

import java.util.List;

public abstract class ProjectileRectangle extends Projectile {
    private int largeurRectangle;
    private double tourX;
    private double tourY;

    public ProjectileRectangle(double x, double y, Ennemi cible, int degats, int largeurRectangle){
        super(x, y, cible, degats);
        this.largeurRectangle = largeurRectangle;
        this.tourX = x;
        this.tourY = y;
    }

    @Override
    public void appliquerImpact(List<Ennemi> ennemis) {
        double impactX = getX();
        double impactY = getY();

        double vecteurAbX = impactX - tourX;
        double vecteurAbY = impactY - tourY;
        double longueurAB = Math.hypot(vecteurAbX, vecteurAbY);

        if (longueurAB == 0) return;

        double uX = vecteurAbX / longueurAB;
        double uY = vecteurAbY / longueurAB;

        double nX = -uY;
        double nY = uX;

        for (Ennemi e : ennemis){
            double ennemiCentreX = e.getX() + 32;
            double ennemiCentreY = e.getY() + 32;

            double vecteurAeX = ennemiCentreX - tourX;
            double vecteurAeY = ennemiCentreY - tourY;

            double projLongueur = (vecteurAeX * uX) + (vecteurAeY * uY);
            double projLargeur = Math.abs((vecteurAeX * nX) + (vecteurAeY * nY));

            // Tolérance élargie pour éviter de rater la cible à cause des virgules flottantes
            if (projLongueur >= -32 && projLongueur <= longueurAB + 64 && projLargeur <= largeurRectangle){
                e.recevoirDegats(getDegats());
            }
        }
    }

    public int getLargeurRectangle() {
        return largeurRectangle;
    }
}