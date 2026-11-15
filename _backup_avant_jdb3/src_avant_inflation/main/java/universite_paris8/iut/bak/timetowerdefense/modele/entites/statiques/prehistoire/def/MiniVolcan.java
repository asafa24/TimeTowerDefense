package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;

import java.util.List;

public class MiniVolcan extends Piege {
    private static final int PRIX_BASE = 25;
    private int degats;
    private int cooldownDegats = 0;

    public MiniVolcan(double x, double y) {
        super(PRIX_BASE, x, y, 5);
        this.degats = 5;
    }

    public static int getPrixBase() {
        return PRIX_BASE;
    }

    @Override
    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles){
        if (cooldownDegats > 0) {
            cooldownDegats--;
        }

        for (int i = ennemis.size() - 1; i >= 0; i--) {
            Ennemi e = ennemis.get(i);
            if (aAtteintPiege(e)) {
                e.appliqueEffet(Effet.SLOW);
                e.appliqueEffet(Effet.BURN);

                if (getPv() > 0 && cooldownDegats <= 0) {
                    recevoirDegats(1);
                    System.out.println("Le volcan a brûlé un ennemi ! PV restants : " + getPv());

                    cooldownDegats = 60;
                }
            }
        }
    }
}