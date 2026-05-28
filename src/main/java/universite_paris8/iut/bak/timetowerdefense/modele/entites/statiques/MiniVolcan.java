package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

public class MiniVolcan extends Piege {
    private int degats;

    public MiniVolcan(int cout, double x, double y, int degats, int pv) {
        super(cout, x, y, pv);
        this.degats = degats;
    }
}