package universite_paris8.iut.bak.timetowerdefense.modele;

import javafx.geometry.Point2D;

import java.util.List;

public class Ennemi extends Entite {
    private int pv;
    private int vitesseBase;
    private int vitesseActuelle;
    private int recompense;

    private List<Point2D> chemin;
    private int etapeActuelle;
    private final int TILE_SIZE = 64;

    public Ennemi(double x, double y, int pv, int vitesseBase, int recompense) {
        super(x, y);
        this.pv = pv;
        this.vitesseBase = vitesseBase;
        this.vitesseActuelle = vitesseBase;
        this.recompense = recompense;
    }

    public Ennemi(double x, double y, int pv, int vitesseBase, int recompense, List<Point2D> chemin) {
        super(x, y);
        this.pv = pv;
        this.vitesseBase = vitesseBase;
        this.vitesseActuelle = vitesseBase;
        this.recompense = recompense;

        this.chemin = chemin;
        etapeActuelle = 0;
    }



    public void avancer(){
        if(chemin == null || etapeActuelle >= chemin.size()) return;

        Point2D caseCible = chemin.get(etapeActuelle);
        double cibleX = caseCible.getX() * TILE_SIZE;
        double cibleY = caseCible.getY() * TILE_SIZE;
        double distanceX = cibleX - this.getX();
        double distanceY = cibleY - this.getY();

        if(distanceX > 0) this.setX(this.getX() + vitesseActuelle);
        else if(distanceX < 0) this.setX(this.getX() - vitesseActuelle);

        if(distanceY > 0) this.setY(this.getY() + vitesseActuelle);
        else if(distanceY < 0) this.setY(this.getY() - vitesseActuelle);

        if(Math.abs(distanceX) <= vitesseActuelle && Math.abs(distanceY) <= vitesseActuelle){
            this.setX(cibleX);
            this.setY(cibleY);
            etapeActuelle++;
        }
    }

    public boolean estMort(){
        return this.pv <= 0;
    }

    public boolean aAtteintLaBase(){
        return chemin != null && etapeActuelle >= chemin.size();
    }

    public int getPv() {
        return pv;
    }
}
