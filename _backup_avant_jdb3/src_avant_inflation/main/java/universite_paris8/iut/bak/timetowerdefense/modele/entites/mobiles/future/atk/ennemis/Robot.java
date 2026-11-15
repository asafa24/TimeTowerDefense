package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.List;

public class Robot extends Ennemi {
    public Robot(List<Point2D> chemin) {
        super(chemin.get(0).getX()*64, chemin.get(0).getY()*64, 70, 2, 10, chemin);
    }
}
