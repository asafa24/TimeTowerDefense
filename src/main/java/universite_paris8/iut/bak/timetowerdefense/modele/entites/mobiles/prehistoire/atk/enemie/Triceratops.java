package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.enemie;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.List;

public class Triceratops extends Ennemi {
    private int tick = 0 ;
    private BooleanProperty shield;
    public Triceratops(double x, double y, int pv, int vitesseBase, int recompense, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, chemin);
    }
    public Triceratops(List<Point2D> chemin) {
        super(150, 1, 30, chemin);
        shield = new SimpleBooleanProperty(false);
    }

    public BooleanProperty getShield(){
        return shield;
    }


    public void agir(List<Ennemi> allies, List<Defense> defenses) {
        if (tick%5 == 0) {
            for (Ennemi ennemy : allies) {
                double dx = ennemy.getX() - this.getX();
                double dy = ennemy.getY() - this.getY();
                if ((dx * dx + dy * dy) < 4 && !(ennemy instanceof Triceratops)) {
                    ennemy.appliqueEffet(Effet.SHIELD);
                    shield.set(true);
                }
            }
        }
        else{
            shield.set(false);
        }
        tick++;

    }
}
