package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.List;

public class Fantassin extends Ennemi {
    public Fantassin(List<Point2D> chemin) {
        super(chemin.get(0).getX()*64, chemin.get(0).getY() * 64, 30, 2, 10, chemin);
    }
}
