package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.effets.EffetBouclier;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteDefense;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteEnnemi;

import java.util.List;

public class Triceratops extends Ennemi {

    private int tick = 0;
    private final BooleanProperty shield;
    private static final double PORTEE = 50;

    public Triceratops(double x, double y, int pv, int vitesseBase, int recompense, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, chemin);
        this.shield = new SimpleBooleanProperty(false);
    }

    public Triceratops(List<Point2D> chemin) {
        super(150, 1, 30, chemin);
        this.shield = new SimpleBooleanProperty(false);
    }

    public BooleanProperty getShield() {
        return shield;
    }

    @Override
    public void agir(AffecteEnnemi epoque1, AffecteDefense epoque2) {
        if (tick % 5 == 0) {
            boolean[] aProtegeQuelquUn = {false};

            epoque1.affecterEnnemis(allie -> {
                // On évite de se protéger soi-même ou un autre Tricératops
                if (!(allie instanceof Triceratops)) {
                    double dx = allie.getX() - this.getX();
                    double dy = allie.getY() - this.getY();
                    double distance = Math.hypot(dx, dy);

                    if (distance <= PORTEE) {
                        allie.ajouterEffet(new EffetBouclier());
                        aProtegeQuelquUn[0] = true;
                    }
                }
            });

            // Met à jour la propriété pour la vue (si vrai, on affiche un effet visuel sur le Tricératops)
            this.shield.set(aProtegeQuelquUn[0]);
        }
        tick++;
    }
}