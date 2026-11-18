package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Destructible;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;

import java.util.List;

public class ElPrimo extends Piege implements Destructible {
    private static final int PRIX_BASE = 60;

    private int pvMax;
    private int pv;
    private int cptAttaque;

    public ElPrimo(double x, double y) {
        super(PRIX_BASE, x, y, 80);
        this.pvMax = 80;
        this.pv = this.pvMax;
        cptAttaque = 30;
    }
    public static int getPrixBase() {
        return prixActuel(ElPrimo.class, PRIX_BASE);
    }

    @Override
    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles) {
        int maCaseX = (int) this.getX();
        int maCaseY = (int) this.getY();

        for (Ennemi e : ennemis) {
            int caseEnnemiX = (int) (e.getCentreX() / 64);
            int caseEnnemiY = (int) (e.getCentreY() / 64);

            if (maCaseX == caseEnnemiX && maCaseY == caseEnnemiY) {
                e.seHeurterA(this);
                if (cptAttaque % 60 == 0) e.recevoirDegats(10);
            }
        }
        cptAttaque++;
    }

    @Override
    public void recevoirDegats(int degats) {
        this.pv -= degats;
        System.out.println("El Primo prend des dégâts ! PV restants : " + this.pv);
    }
    @Override
    public boolean estMort() {
        return this.pv <= 0;
    }
    public int getPv() {
        return pv;
    }
}