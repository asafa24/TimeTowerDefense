package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Boss;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

import java.util.List;

public class Tyrannosaurus extends Boss {

    public Tyrannosaurus(double x, double y, int pv, int vitesseBase, int recompense, int tempsRecharge, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, tempsRecharge, chemin);
    }
    public Tyrannosaurus(List<Point2D> chemin) {
        super(1200, 1, 400, 900, chemin);
        setX(0);
        setY(64 * 9);
    }

    @Override
    public void competence(List<Ennemi> ennemis, List<Defense> defenses) {
        for(Defense tour : defenses){
            if(tour instanceof Tour) ((Tour) tour).setStun(300);
        }

    }
}
