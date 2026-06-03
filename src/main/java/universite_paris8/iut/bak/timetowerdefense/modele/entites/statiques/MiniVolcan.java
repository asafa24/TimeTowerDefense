package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

import java.util.List;

public class MiniVolcan extends Piege {
    private int degats;


    public MiniVolcan(int cout, double x, double y, int degats, int pv) {
        super(cout, x, y, pv);
        this.degats = degats;
    }

    @Override
    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles){
        boolean volcanBlesseCeTick = false;
        for (int i = ennemis.size() - 1; i >= 0; i--) {
            Ennemi e = ennemis.get(i);
            if (aAtteintPiege(e)) {
                e.appliqueEffet(Effet.SLOW);
                e.appliqueEffet(Effet.BURN);
                if (getPv() > 0) {
                    recevoirDegats(1);
                }
            }
        }
    }
}
