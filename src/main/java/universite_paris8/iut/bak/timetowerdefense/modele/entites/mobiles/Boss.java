package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Tour;

import java.util.List;

public abstract class Boss extends Ennemi{

    private double tempsRecharge;

    public Boss(double x, double y, int pv, int vitesseBase, int recompense, int tempsRecharge, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, chemin);
        this.tempsRecharge = tempsRecharge;
    }

    public abstract void competence(List<Tour> tours);



}
