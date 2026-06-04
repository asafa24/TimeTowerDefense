package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourStun;

public  class LanceFilet extends TourStun {
    public static IntegerProperty cout = new SimpleIntegerProperty(150); ;
    public LanceFilet(double x, double y) {
        super(cout.get(), x, y, 10, 128, 200, 180);
    }

    @Override
    public void inflation(){
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }


}
