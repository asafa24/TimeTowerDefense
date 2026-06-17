package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.List;

public class IA extends Ennemi {
    public IA(List<Point2D> chemin) {
        super(chemin.get(0).getX()*64, chemin.get(0).getY()*64, 2000, 1, 200, chemin);
    }

    @Override
    public void agir(List<Ennemi> ennemis, List<Defense> defenses) {
        for (Ennemi e : ennemis) {
            if (e != this) {
                double distance = Math.hypot(this.getX() - e.getX(), this.getY() - e.getY());
                if (distance < 150) {
                    e.ajouterPv(1); // Augmente les PV des alliés proches
                }
            }
        }
    }
}
