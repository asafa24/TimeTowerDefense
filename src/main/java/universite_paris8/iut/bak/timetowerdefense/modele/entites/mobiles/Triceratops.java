package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.List;

public class Triceratops extends Ennemi {
    public Triceratops(double x, double y, int pv, int vitesseBase, int recompense, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, chemin);
    }
    public Triceratops(List<Point2D> chemin) {
        super(150, 1, 30, chemin);
    }
}
