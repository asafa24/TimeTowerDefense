package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Destructible;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

import java.util.List;

public class PorteDeSable extends Piege implements Destructible {
    private static final int PRIX_BASE = 40;

    private int pvMax;
    private int pv;

    public PorteDeSable(double x, double y) {
        super(PRIX_BASE, x, y, 100);
        this.pvMax = 100;
        this.pv = this.pvMax;
    }

    public static int getPrixBase() {
        return PRIX_BASE;
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
            }
        }
    }

    @Override
    public void recevoirDegats(int degats) {
        this.pv -= degats;
        System.out.println("La porte de sable prend des dégâts ! PV restants : " + this.pv);
    }

    @Override
    public boolean estMort() {
        return this.pv <= 0;
    }

    public int getPv() {
        return pv;
    }
}