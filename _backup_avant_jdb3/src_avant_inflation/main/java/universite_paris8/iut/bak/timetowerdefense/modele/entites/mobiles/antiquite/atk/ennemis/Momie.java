package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.List;

public class Momie extends Ennemi {
    public Momie(int x, int y, List<Point2D> chemin) {
        super(x, y, chemin);
    }

    public Momie(List<Point2D> chemin){
        super( 25, 2, 5, chemin);
        setX(64 * 10);
        setY(64 * 10);
    }

}
