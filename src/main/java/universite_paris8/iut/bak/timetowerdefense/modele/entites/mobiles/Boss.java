package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteDefense;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteEnnemi;

import java.util.List;

public abstract class Boss extends Ennemi {
    private int tempsRecharge;
    protected int compteurCompetence;

    public Boss(double x, double y, int pv, int vitesseBase, int recompense, int tempsRecharge, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, chemin);
        this.tempsRecharge = tempsRecharge;
        this.compteurCompetence = 60;
    }

    public Boss(int pv, int vitesseBase, int recompense, int tempsRecharge, List<Point2D> chemin) {
        super(pv, vitesseBase, recompense, chemin);
        this.tempsRecharge = tempsRecharge;
        this.compteurCompetence = 60;
    }

    @Override
    public void agir(AffecteEnnemi epoque1, AffecteDefense epoque2) {
        if (compteurCompetence > 0) {
            compteurCompetence--;
        } else {
            competence(epoque1, epoque2);
            compteurCompetence = tempsRecharge;
        }
    }

    public abstract void competence(AffecteEnnemi epoque1, AffecteDefense epoque2);

    public int getTempsRecharge() {
        return tempsRecharge;
    }

    public void setTempsRecharge(int tempsRecharge) {
        this.tempsRecharge = tempsRecharge;
    }
}
