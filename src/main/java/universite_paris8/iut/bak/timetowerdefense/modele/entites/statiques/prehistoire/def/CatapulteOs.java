package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;

public class CatapulteOs extends TourCercle {
    public CatapulteOs(double x, double y) {
        super(cout, x, y, 40, 128, 200, 180);
    }

    @Override
    public void inflation(){
        cout = (int) Math.floor(cout * 1.1);
        super.setCout(cout);
    }
}
