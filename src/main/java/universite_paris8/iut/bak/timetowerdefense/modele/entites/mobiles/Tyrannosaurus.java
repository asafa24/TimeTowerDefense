package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;

import java.util.List;

public class Tyrannosaurus extends Boss{
    private int frameCount;

    public Tyrannosaurus(double x, double y, int pv, int vitesseBase, int recompense, int tempsRecharge, List<Point2D> chemin) {
        super(x, y, pv, vitesseBase, recompense, tempsRecharge, chemin);
        frameCount = 60;
    }

    @Override
    public void competence(List<Defense> tours) {
        if(frameCount > 0){
            frameCount--;
            return;
        }
        for(Defense tour : tours){
            if(tour instanceof Tour) ((Tour) tour).setStun(300);
        }
        frameCount = getTempsRecharge();
    }
}
