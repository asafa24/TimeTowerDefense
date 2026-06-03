package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import java.util.List;

public class Velociraptor extends Ennemi {
    private int tick = 0 ;
    private final double VITTESSE_MIN = 2;
    public Velociraptor(double x, double y, int pv, int vitesseBase, int recompense, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, chemin);
    }
    public Velociraptor(List<Point2D> chemin) {
        super( 42, 4.5, 20, chemin,"images/tiles/prehistoire/d01.png");
    }






    public void avancer(){
        super.avancer();
        if (tick%30 == 0 && super.getVitesseBase() > VITTESSE_MIN){
            super.setVitesseBase(super.getVitesseBase() - 0.1);
        }
        tick++;
    }

}
