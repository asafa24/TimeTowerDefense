package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.enemie;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.List;

public class Compsognathus extends Ennemi {
    public Compsognathus(List<Point2D> chemin) {
        super( 25, 2, 5, chemin);
    }
}
