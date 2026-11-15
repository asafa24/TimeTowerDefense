package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public abstract class Defense extends Entite {
    protected IntegerProperty cout;
    private BooleanProperty selectionnee;


    public Defense(int cout, double x, double y) {
        super(x, y);
        this.cout = new SimpleIntegerProperty(cout);
        selectionnee = new SimpleBooleanProperty(false);
    }


    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles){}

    public int getCout() {
        return cout.get();
    }
    public BooleanProperty selectionneeProperty() {
        return selectionnee;
    }
    public boolean isSelectionnee() {
        return selectionnee.get();
    }
    public IntegerProperty coutProperty() {
        return cout;
    }
    public void setSelectionnee(boolean selectionnee) {
        this.selectionnee.set(selectionnee);
    }

    public void setCout(int cout) {
        this.cout.set(cout);
    }

    public void inflation() {
        this.setCout((int) Math.floor(this.getCout() * 1.1));
    }
}



















