package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Boss;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteDefense;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteEnnemi;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

import java.util.List;

public class Tyrannosaurus extends Boss {

    public Tyrannosaurus(double x, double y, int pv, int vitesseBase, int recompense, int tempsRecharge,
            List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, tempsRecharge, chemin);
    }

    public Tyrannosaurus(List<Point2D> chemin) {
        super(1200, 1, 400, 900, chemin);
        setX(0);
        setY(64 * 9);
    }

    @Override
    public void competence(AffecteEnnemi epoque1, AffecteDefense epoque2) {
        epoque2.affecterDefenses(d -> {
            if (d instanceof Tour t) {
                t.setStun(300);
            }
        });

    }
}
