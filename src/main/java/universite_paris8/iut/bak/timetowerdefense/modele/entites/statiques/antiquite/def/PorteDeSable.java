package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;

public class PorteDeSable extends Piege{

    public static IntegerProperty cout = new SimpleIntegerProperty(40);

    private int pv;

    public PorteDeSable(double x, double y) {
        super(cout.get(), x, y,100);
        this.pv = 100;
    }

    @Override
    public void recevoirDegats(int degats) {
        this.pv -= degats;
        System.out.println("La porte de sable prend des dégâts ! PV restants : " + this.pv);
    }

    @Override
    public boolean estMort() {
        return this.pv <= 0;
    }

    @Override
    public void inflation() {
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    public static IntegerProperty coutPropertyPorteSable() {
        return cout;
    }

    public int getPv() {
        return pv;
    }
}