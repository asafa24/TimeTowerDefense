package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.List;

public class Cavalier extends Ennemi {
    private int vieCheval;

    public Cavalier(List<Point2D> chemin) {
        super(chemin.get(0).getX() * 64, chemin.get(0).getY() * 64, 180, 1, 20, chemin);
        this.vieCheval = 80;
    }

    @Override
    public void agir(List<Ennemi> ennemis, List<Defense> defenses) {
        if(getPv() - vieCheval >= 60){
            setVitesseActuelle(getVitesseBase()*2);
        } else {
            setVitesseActuelle(getVitesseBase());
        }
    }
}
