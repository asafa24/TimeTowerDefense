package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Triceratops;

import java.util.List;

public class Chevalier extends Ennemi {
    public Chevalier(List<Point2D> chemin) {
        super(chemin.get(0).getX() * 64, chemin.get(0).getY() * 64, 150, 1, 20, chemin);
    }
}