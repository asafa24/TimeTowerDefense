package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

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
    public void agir(List<Ennemi> allies, List<Defense> defenses) {
            if(compteurCompetence > 0){
                compteurCompetence--;
            } else{
                competence(allies, defenses);
                compteurCompetence = tempsRecharge;
            }
    }

    public abstract void competence(List<Ennemi> ennemis, List<Defense> defenses);


    public int getTempsRecharge() {
        return tempsRecharge;
    }

    public void setTempsRecharge(int tempsRecharge) {
        this.tempsRecharge = tempsRecharge;
    }
}
