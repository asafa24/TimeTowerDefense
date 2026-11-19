package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Boss;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteDefense;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteEnnemi;

import java.util.List;

public class Roi extends Boss {
    public Roi(List<Point2D> chemin) {
        super(1500, 1, 200, 180, chemin);
        setX(6 * 64);
        setY(10 * 64);
    }

    @Override
    public void competence(AffecteEnnemi epoque1, AffecteDefense epoque2) {
        epoque1.affecterEnnemis(e -> {
            e.appliqueEffet(Effet.SPEED_BOOST);
        });
        System.out.println("Speed Boost activéw");
    }
}
