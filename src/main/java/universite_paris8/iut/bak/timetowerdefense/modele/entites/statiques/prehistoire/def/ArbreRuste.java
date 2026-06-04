package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;



public class ArbreRuste extends Tour {
    public static IntegerProperty cout = new SimpleIntegerProperty(50);
    public ArbreRuste(double x, double y) {
        super(cout.get(), x, y, 10, 64, 60);
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
