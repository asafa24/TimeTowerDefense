package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.List;

public class SoldatRouge extends Ennemi {
    public SoldatRouge(List<Point2D> chemin) {
        super(64*6, 64*10, 30, 2, 25, chemin);
    }
}
