package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Ennemi;

public class MiniVolcan extends Piege{
    private int degats;
    public MiniVolcan(int cout, double x, double y ,int degats) {
        super(cout, x, y);
        this.degats = degats;
    }

}
