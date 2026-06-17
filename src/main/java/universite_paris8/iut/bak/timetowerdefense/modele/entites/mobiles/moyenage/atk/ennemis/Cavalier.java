package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.List;

public class Cavalier extends Ennemi {
    private int vieCheval;
    private double vitesseCavalier;

    public Cavalier(List<Point2D> chemin) {
        super(chemin.get(0).getX() * 64, chemin.get(0).getY() * 64, 180, 1, 20, chemin);
        this.vieCheval = 80;
        vitesseCavalier = getVitesseBase();
        setVitesseBase(getVitesseBase()*1.5);

    }

    @Override
    public void agir(List<Ennemi> ennemis, List<Defense> defenses) {
        if(getPv() - vieCheval < vieCheval){
            setVitesseActuelle(vitesseCavalier);
        }
    }
}
