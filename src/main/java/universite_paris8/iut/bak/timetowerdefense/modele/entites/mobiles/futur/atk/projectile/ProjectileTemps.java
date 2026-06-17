package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.projectiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileRectangle;

import java.util.List;

public class ProjectileTemps extends ProjectileRectangle {
    private double startX;
    private double startY;

    public ProjectileTemps(double x, double y, Ennemi cible, int degats, int largeur) {
        super(x, y, cible, degats, largeur);
        this.startX = x;
        this.startY = y;
    }

    @Override
    public void appliquerImpact(List<Ennemi> ennemis) {
        double impactX = getX();
        double impactY = getY();

        double vecteurAbX = impactX - startX;
        double vecteurAbY = impactY - startY;
        double longueurAB = Math.hypot(vecteurAbX, vecteurAbY);

        if (longueurAB == 0) return;

        double uX = vecteurAbX / longueurAB;
        double uY = vecteurAbY / longueurAB;

        double nX = -uY;
        double nY = uX;

        for (Ennemi e : ennemis) {
            double ennemiCentreX = e.getX() + 32;
            double ennemiCentreY = e.getY() + 32;

            double vecteurAeX = ennemiCentreX - startX;
            double vecteurAeY = ennemiCentreY - startY;

            double projLongueur = (vecteurAeX * uX) + (vecteurAeY * uY);
            double projLargeur = Math.abs((vecteurAeX * nX) + (vecteurAeY * nY));

            if (projLongueur >= -32 && projLongueur <= longueurAB + 64 && projLargeur <= getLargeurRectangle()) {
                e.recevoirDegats(getDegats());
                e.appliqueEffet(Effet.SLOW);
            }
        }
    }
}