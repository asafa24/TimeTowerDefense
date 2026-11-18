package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Boss;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteDefense;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteEnnemi;

import java.util.List;
import java.util.Random;

public class Dictateur extends Boss {
    public Dictateur(List<Point2D> chemin) {
        super(6 * 64, 10 * 64, 1500, 600, chemin);
    }

    @Override
    public void competence(AffecteEnnemi epoque1, AffecteDefense epoque2) {
        // Random r = new Random();
        // int i = 0;
        // do {
        // i = r.nextInt(defenses.size());
        // } while (!(defenses.get(i) instanceof Tour));
        // ((Tour) defenses.get(i)).desactiver();
        epoque2.affecterDefenses(d -> {
            if (d instanceof Tour t) {
                t.desactiver();
                return;
            }
        });

    }

}

