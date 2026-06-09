package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Antique;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;

public class TotemFlechette extends Tour {
    public static IntegerProperty cout = new SimpleIntegerProperty(50);
    public TotemFlechette(double x, double y) {
        super(cout.get(), x, y, 10, 128, 30);
    }

    @Override
    public void inflation() {
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    public static IntegerProperty coutPropertyFlechette() {
        return cout;
    }
}
