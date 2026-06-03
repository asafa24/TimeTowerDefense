package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Entite;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

import java.util.List;

public abstract class Defense extends Entite {
    private int cout;
    private BooleanProperty selectionnee;


    public Defense(int cout, double x, double y) {
        super(x, y);
        this.cout = cout;
        selectionnee = new SimpleBooleanProperty(false);
    }
    public Defense(int cout, double x, double y,String sprite) {
        super(x, y,sprite);
        this.cout = cout;
        selectionnee = new SimpleBooleanProperty(false);
    }

    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles){}

    public int getCout() {
        return cout;
    }
    public BooleanProperty selectionneeProperty() {
        return selectionnee;
    }

    public boolean isSelectionnee() {
        return selectionnee.get();
    }

    public void setSelectionnee(boolean selectionnee) {
        this.selectionnee.set(selectionnee);
    }

    public void setCout(int cout) {
        this.cout = cout;
    }
}