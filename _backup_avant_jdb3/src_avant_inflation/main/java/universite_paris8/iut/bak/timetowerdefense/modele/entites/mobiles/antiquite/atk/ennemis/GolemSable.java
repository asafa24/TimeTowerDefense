package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.ArrayList;
import java.util.List;

public class GolemSable extends Ennemi {

    public GolemSable(int x, int y, List<Point2D> chemin) {
        super(x, y, chemin);
    }
    public GolemSable(List<Point2D> chemin){
        super( 150, 1, 25, chemin);
        setX(64 * 10);
        setY(64 * 10);
    }

    @Override
    public List<Ennemi> onDeath() {
        List<Ennemi> golimes = new ArrayList<>();
        Ennemi a = new Golime(this.getX(), this.getY(), this.getChemin());
        a.setEtapeActuelle(this.getEtapeActuelle());
        golimes.add(a);
        Ennemi b = new Golime(this.getX() + 20, this.getY() + 20, this.getChemin());
        b.setEtapeActuelle(this.getEtapeActuelle());
        golimes.add(b);
        return golimes;
    }
}
