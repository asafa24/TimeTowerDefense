package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Destructible;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

import java.util.List;

public class Piege extends Defense implements Destructible {
    private int pv;

    public Piege(int cout, double x, double y, int pv) {
        super(cout, x, y);
        this.pv = pv;
    }

    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles) {

    }

    public boolean aAtteintPiege(Ennemi e){
        double centreX = (getX() * 64);
        double centreY = (getY() * 64);
        double distance = Math.hypot(centreX - e.getCentreX(), centreY - e.getCentreY());
        return distance <= 32;
    }

    @Override
    public int getPv() {
        return this.pv;
    }

    @Override
    public boolean estMort() {
        return this.pv <= 0;
    }

    @Override
    public void recevoirDegats(int dgt) {
        this.pv -= dgt;
    }
}