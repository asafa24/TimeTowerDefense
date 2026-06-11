package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.enemie;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.List;

public class Triceratops extends Ennemi {

    private int tick = 0;
    private final BooleanProperty shield; // 'final' car la référence ne change pas

    // Portée de l'effet au carré (ex: portée de 50 pixels -> 50 * 50 = 2500)
    private static final double PORTEE_CARRE = 2500.0;

    public Triceratops(double x, double y, int pv, int vitesseBase, int recompense, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, chemin);
        this.shield = new SimpleBooleanProperty(false);
    }

    public Triceratops(List<Point2D> chemin) {
        super(150, 1, 30, chemin); // PV, Vitesse, Recompense ?
        this.shield = new SimpleBooleanProperty(false);
    }

    public BooleanProperty getShield() {
        return shield;
    }

    @Override
    public void agir(List<Ennemi> allies, List<Defense> defenses) {
        // Optionnel : On fait avancer le Tricératops via la classe parente si super.agir() existe
        // super.agir(allies, defenses);

        // On ne vérifie pas à chaque frame pour optimiser les performances (tous les 5 ticks)
        if (tick % 5 == 0) {
            boolean aProtegeQuelquUn = false;

            for (Ennemi allie : allies) {
                // On évite de se protéger soi-même ou un autre Tricératops
                if (allie != this && !(allie instanceof Triceratops)) {
                    double dx = allie.getX() - this.getX();
                    double dy = allie.getY() - this.getY();
                    double distanceCarre = (dx * dx) + (dy * dy);

                    // Si l'allié est à portée
                    if (distanceCarre <= PORTEE_CARRE) {
                        allie.appliqueEffet(Effet.SHIELD);
                        aProtegeQuelquUn = true;
                    }
                }
            }

            // Met à jour la propriété pour la vue (si vrai, on affiche un effet visuel sur le Tricératops)
            this.shield.set(aProtegeQuelquUn);
        }

        tick++;
    }
}