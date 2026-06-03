package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.List;

public abstract class Boss extends Ennemi {

    private int tempsRecharge;

    public Boss(double x, double y, int pv, int vitesseBase, int recompense, int tempsRecharge, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, chemin);
        this.tempsRecharge = tempsRecharge;
    }

    public abstract void competence(List<Defense> tours);


    public int getTempsRecharge() {
        return tempsRecharge;
    }

    public void setTempsRecharge(int tempsRecharge) {
        this.tempsRecharge = tempsRecharge;
    }
}
