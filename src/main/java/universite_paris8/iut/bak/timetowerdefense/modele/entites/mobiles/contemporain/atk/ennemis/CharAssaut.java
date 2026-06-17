package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.List;

public class CharAssaut extends Ennemi {
    public CharAssaut(List<Point2D> chemin) {
        super(6 * 64, 10 * 64, 400, 1, 150, chemin);
    }
    
    @Override
    public void agir(List<Ennemi> ennemis, List<Defense> defenses) {
        double ratioPv = (double) this.getPv() / this.getPvMax();
        double multiplicateurVitesse = 1.0 + ((1.0 - ratioPv) * 2.0);
        this.setVitesseBase(multiplicateurVitesse);
    }
}
