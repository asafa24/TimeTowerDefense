package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Entite;

public abstract class Defense extends Entite {
    private int cout;

    public Defense(int cout, double x, double y) {
        super(x, y);
        this.cout = cout;
    }

    public int getCout() {
        return cout;
    }
}