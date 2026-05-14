package universite_paris8.iut.bak.timetowerdefense.modele;

public class Ennemi extends Entite {
    private int pv;
    private int vitesseBase;
    private int vitesseActuelle;
    private int recompense;



    public Ennemi(double x, double y, int pv, int vitesseBase, int recompense) {
        super(x, y);
        this.pv = pv;
        this.vitesseBase = vitesseBase;
        this.vitesseActuelle = vitesseBase;
        this.recompense = recompense;
    }

    public void avancer(){
        this.setX(this.getX() + vitesseActuelle);
        this.setY(this.getY() + vitesseActuelle);
    }

    public boolean estMort(){
        return this.pv <= 0;
    }

}
