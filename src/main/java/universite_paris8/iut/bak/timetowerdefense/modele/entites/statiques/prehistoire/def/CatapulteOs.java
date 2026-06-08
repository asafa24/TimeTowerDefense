package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;

public class CatapulteOs extends TourCercle {
    public static IntegerProperty cout = new SimpleIntegerProperty(150);
    public CatapulteOs(double x, double y) {
        super(cout.get(), x, y, 40, 128, 200, 96);
    }

    @Override
    public void inflation(){
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }
    public static IntegerProperty coutPropertyArbre() {
        return cout;
    }
}
