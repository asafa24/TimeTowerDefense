package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Defense extends Entite {
    private static final double TAUX_INFLATION = 1.1;
    private static final Map<Class<? extends Defense>, Integer> prixActuels = new HashMap<>();

    protected IntegerProperty cout;
    private BooleanProperty selectionnee;


    public Defense(int cout, double x, double y) {
        super(x, y);
        this.cout = new SimpleIntegerProperty(prixActuels.getOrDefault(getClass(), cout));
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

    protected double getTauxInflation() {
        return TAUX_INFLATION;
    }

    public void inflation() {
        int nouveauPrix = (int) Math.floor(this.getCout() * getTauxInflation());
        this.setCout(nouveauPrix);
        prixActuels.put(getClass(), nouveauPrix);
    }

    public static int prixActuel(Class<? extends Defense> type, int prixBase) {
        return prixActuels.getOrDefault(type, prixBase);
    }

    public static void reinitialiserPrix() {
        prixActuels.clear();
    }
}



















