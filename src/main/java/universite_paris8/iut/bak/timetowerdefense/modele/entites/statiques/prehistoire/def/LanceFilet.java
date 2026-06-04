package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourStun;

public  class LanceFilet extends TourStun {
    public LanceFilet(double x, double y) {
        super(200, x, y, 10, 128, 200, 180);
    }

    @Override
    public void inflation(){
        cout = (int) Math.floor(cout * 1.1);
        super.setCout(cout);
    }


}
