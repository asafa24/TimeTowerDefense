package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.List;

public class Golime extends Ennemi {

    public Golime(double x, double y, int pv, int vitesseBase, int recompense, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, chemin);
    }

    public Golime(List<Point2D> chemin) {
        super( 20, 1, 25, chemin);
        setX(64 * 10);
        setY(64 * 10);
    }
    public Golime(double x , double y, List<Point2D> chemin) {
        super( x,y,20, 1, 25, chemin);
    }

}
