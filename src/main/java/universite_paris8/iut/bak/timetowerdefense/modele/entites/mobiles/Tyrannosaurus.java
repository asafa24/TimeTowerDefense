package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Tour;

import java.util.List;

public class Tyrannosaurus extends Boss{
    public Tyrannosaurus(double x, double y, int pv, int vitesseBase, int recompense, int tempsRecharge, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, tempsRecharge, chemin);
    }

    @Override
    public void competence(List<Tour> tours) {
        for(Tour tour : tours){
            tour.setStun(true);
        }
    }
}
