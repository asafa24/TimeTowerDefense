package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;



public class ArbreRuste extends Tour {
    public static int cout = 50;
    public ArbreRuste(double x, double y) {
        super(cout, x, y, 10, 64, 60);
    }

    @Override
    public void inflation(){
        cout = (int) Math.floor(cout * 1.1);
        super.setCout(cout);
    }

}
