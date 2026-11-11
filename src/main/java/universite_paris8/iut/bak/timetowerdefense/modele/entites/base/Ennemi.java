package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ennemi extends Entite implements Destructible {
    private int pv, pvMax;
    private DoubleProperty pvProp;
    private IntegerProperty direction;
    private double vitesseBase;
    private double vitesseActuelle;
    private int recompense;
    // Retrait du premier 'estBloque' dupliqué
    private boolean possedeBouclier = false;

    private final List<Effets> effetsActifs = new ArrayList<>();

    private List<Point2D> chemin;
    private int etapeActuelle;
    private final int TILE_SIZE = 64;

    private int degatsSurMur = 10;
    private int cadenceAttaque = 60;
    private int compteurAttaque = 0;
    private boolean estBloque = false;

    public Ennemi(double x, double y, int pv, double vitesseBase, int recompense, List<Point2D> chemin) {
        super(x, y);
        this.pv = pv;
        this.pvMax = pv;
        this.vitesseBase = vitesseBase;
        this.vitesseActuelle = vitesseBase;
        this.recompense = recompense;

        this.chemin = chemin;
        this.etapeActuelle = 0;
        this.pvProp = new SimpleDoubleProperty(pv);
        this.direction = new SimpleIntegerProperty(-1);
    }

    public Ennemi(int pv, double vitesseBase, int recompense, List<Point2D> chemin) {
        this(0.0, 0.0, pv, vitesseBase, recompense, chemin);
    }

    public Ennemi(int x, int y, List<Point2D> chemin) {
        this(x, y, 25, 2.0, 5, chemin);
    }

    public void avancer() {
        actualiserEffets();

        if (this.vitesseActuelle <= 0 || this.estBloque) {
            this.estBloque = false;
            return;
        }

        deplacerVersProchaineEtape();
    }

    private void deplacerVersProchaineEtape() {
        if (aAtteintLaBase()) {
            return;
        }
        Point2D caseCible = chemin.get(etapeActuelle);
        double cibleX = caseCible.getX() * TILE_SIZE;
        double cibleY = caseCible.getY() * TILE_SIZE;
        double distanceX = cibleX - this.getX();
        double distanceY = cibleY - this.getY();

        mettreAJourPosition(distanceX, distanceY);
        verifierArriveeEtape(cibleX, cibleY, distanceX, distanceY);
    }

    private void mettreAJourPosition(double distanceX, double distanceY) {
        deplacerX(distanceX);
        deplacerY(distanceY);
        mettreAJourDirection(distanceX);
    }

    private void deplacerX(double dx) {
        if (dx > 0) this.setX(this.getX() + this.vitesseActuelle);
        else if (dx < 0) this.setX(this.getX() - this.vitesseActuelle);
    }

    private void deplacerY(double dy) {
        if (dy > 0) this.setY(this.getY() + this.vitesseActuelle);
        else if (dy < 0) this.setY(this.getY() - this.vitesseActuelle);
    }

    private void mettreAJourDirection(double dx) {
        if (dx > 0) this.direction.set(-1);
        else if (dx < 0) this.direction.set(1);
    }

    private void verifierArriveeEtape(double cibleX, double cibleY, double dx, double dy) {
        if (estSurCible(dx, dy)) {
            atteindreEtape(cibleX, cibleY);
        }
    }

    private boolean estSurCible(double dx, double dy) {

        return this.vitesseActuelle > 0
                && Math.abs(dx) <= this.vitesseActuelle
                && Math.abs(dy) <= this.vitesseActuelle;
    }

    private void atteindreEtape(double cibleX, double cibleY) {
        this.setX(cibleX);
        this.setY(cibleY);
        this.etapeActuelle++;
    }

    public void agir(List<Ennemi> ennemis, List<Defense> defenses) {
    }

    public void seHeurterA(Destructible mur) {
        this.estBloque = true;

        if (compteurAttaque >= cadenceAttaque) {
            mur.recevoirDegats(this.degatsSurMur);
            compteurAttaque = 0;
            System.out.println(this.getClass().getSimpleName() + " tape la porte !");
        } else {
            compteurAttaque++;
        }
    }

    public void recevoirDegats(int dgt) {
        this.pv -= dgt;
        if (this.pv < 0) this.pv = 0;
        this.pvProp.set(pv);
    }

    public boolean estMort() {
        return this.pv <= 0;
    }

    public boolean aAtteintLaBase() {
        return chemin != null && etapeActuelle >= chemin.size();
    }

    public List<Ennemi> onDeath() {
        return Collections.emptyList();
    }

    public int getPv() {
        return pv;
    }

    public void ajouterPv(int pv) {
        this.pv += pv;
    }

    public int getPvMax() {
        return pvMax;
    }

    public IntegerProperty getDirectionProperty() {
        return direction;
    }

    public DoubleProperty getPvPropProperty() {
        return pvProp;
    }

    public int getRecompense() {
        return recompense;
    }

    public int getEtapeActuelle() {
        return etapeActuelle;
    }

    public void setEtapeActuelle(int etapeActuelle) {
        this.etapeActuelle = etapeActuelle;
    }

    private double calculerDemiTuile() {
        return TILE_SIZE / 2.0;
    }

    public double getCentreX() {
        return this.getX() + calculerDemiTuile();
    }

    public double getCentreY() {
        return this.getY() + calculerDemiTuile();
    }

    public List<Point2D> getChemin() {
        return chemin;
    }

    public double getVitesseBase() {
        return vitesseBase;
    }

    public void setVitesseBase(double vitesseBase) {
        this.vitesseBase = vitesseBase;
    }

    public void setVitesseActuelle(double vitesseActuelle) {
        this.vitesseActuelle = vitesseActuelle;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void setRecompense(int recompense) {
        this.recompense = recompense;
    }

    public void ajouterEffet(Effets nouvelEffet) {
        nouvelEffet.effetAplication(this);
        this.effetsActifs.add(nouvelEffet);
    }

    private void actualiserEffets() {
        this.vitesseActuelle = this.vitesseBase;

        for (int i = effetsActifs.size() - 1; i >= 0; i--) {
            Effets effet = effetsActifs.get(i);
            effet.tick(this);
            if (effet.estFini()) {
                effetsActifs.remove(i);
            }
        }
    }

    public double getVitesseActuelle() {
        return vitesseActuelle;
    }

    public boolean possedeBouclier() {
        return possedeBouclier;
    }

    public void setPossedeBouclier(boolean possede) {
        this.possedeBouclier = possede;
    }
}