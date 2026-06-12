package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.List;

public class Chacal extends Ennemi {

    private int tick = 0 ;
    private final double VITTESSE_MIN = 2;

    public Chacal(int x, int y, List<Point2D> chemin) {
        super(x, y, chemin);
    }

    public Chacal(List<Point2D> chemin){
        super(10*64, 10*64, chemin);
        setVitesseBase(4.5);
        setPv(40);
        setRecompense(25);
    }

    public void avancer(){
        super.avancer();
        if (tick%30 == 0 && super.getVitesseBase() > VITTESSE_MIN){
            super.setVitesseBase(super.getVitesseBase() - 0.1);
        }
        tick++;
    }
}
