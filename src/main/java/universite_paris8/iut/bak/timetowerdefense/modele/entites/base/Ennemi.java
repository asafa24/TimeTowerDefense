package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.Collections;
import java.util.List;

public class Ennemi extends Entite implements Destructible {
    private int pv, pvMax;
    private DoubleProperty pvProp;
    private IntegerProperty direction;
    private double vitesseBase;
    private double vitesseActuelle;
    private int recompense;

    private int dureeStunRestante = 0;
    private int dureeSlowRestante = 0;
    private int dureeBrulageRestante = 0;
    private int dureeSpeedBoostRestante = 0;
    private boolean shield = false;

    private List<Point2D> chemin;
    private int etapeActuelle;
    private final int TILE_SIZE = 64;

    private int degatsSurMur = 10;
    private int cadenceAttaque = 60;
    private int compteurAttaque = 0;
    private boolean estBloque = false;

    public Ennemi(double x, double y, int pv, int vitesseBase, int recompense, List<Point2D> chemin) {
        super(x, y);
        this.pv = pv;
        this.vitesseBase = vitesseBase;
        this.vitesseActuelle = vitesseBase;
        this.recompense = recompense;

        this.chemin = chemin;
        etapeActuelle = 0;
        this.pvMax = pv;
        this.pvProp = new SimpleDoubleProperty(pv);
        this.direction = new SimpleIntegerProperty(-1);
    }

    public Ennemi(int pv, double vitesseBase, int recompense, List<Point2D> chemin) {
        super();
        super.setX(0);
        super.setY(64 * 9);
        this.pv = pv;
        this.vitesseBase = vitesseBase;
        this.vitesseActuelle = vitesseBase;
        this.recompense = recompense;

        this.chemin = chemin;
        etapeActuelle = 0;
        this.pvMax = pv;
        this.pvProp = new SimpleDoubleProperty(pv);
        this.direction = new SimpleIntegerProperty(-1);
    }

    public Ennemi(int x , int y, List<Point2D> chemin) {
        super();
        super.setX(x);
        super.setY(y);
        this.pv = 25;
        this.vitesseBase = 2;
        this.vitesseActuelle = 2;
        this.recompense = 5;
        this.chemin = chemin;
        etapeActuelle = 0;
        this.pvMax = pv;
        this.pvProp = new SimpleDoubleProperty(pv);
        this.direction = new SimpleIntegerProperty(-1);
    }


//     Fait avancer l'ennemi le long de son chemin.
//     Gère également l'application des altérations d'état (brûlure, étourdissement, ralentissement)
//     et l'arrêt s'il rencontre un obstacle.
    public void avancer() {
        if (this.dureeBrulageRestante > 0) {
            this.dureeBrulageRestante--;
            if (this.dureeBrulageRestante % 10 == 0) {
                this.recevoirDegats(1);
            }
        }

        if (this.dureeStunRestante > 0) {
            this.dureeStunRestante--;
            this.vitesseActuelle = 0;
            return; // Bloque le mouvement tant qu'étourdi
        }

        this.vitesseActuelle = this.vitesseBase;

        if (this.dureeSlowRestante > 0) {
            this.dureeSlowRestante--;
            this.vitesseActuelle *= 0.5;
        }

        if (this.dureeSpeedBoostRestante > 0) {
            this.dureeSpeedBoostRestante--;
            this.vitesseActuelle *= 1.3;
        }

        if (this.estBloque) {
            this.estBloque = false;
            return;
        }

        // 5. Déplacement physique
        if (chemin == null || etapeActuelle >= chemin.size()) return;

        Point2D caseCible = chemin.get(etapeActuelle);
        double cibleX = caseCible.getX() * TILE_SIZE;
        double cibleY = caseCible.getY() * TILE_SIZE;
        double distanceX = cibleX - this.getX();
        double distanceY = cibleY - this.getY();

        if (distanceX > 0) {
            this.setX(this.getX() + vitesseActuelle);
            this.direction.set(-1);
        } else if (distanceX < 0) {
            this.setX(this.getX() - vitesseActuelle);
            this.direction.set(1);
        }

        if (distanceY > 0) this.setY(this.getY() + vitesseActuelle);
        else if (distanceY < 0) this.setY(this.getY() - vitesseActuelle);

        if (vitesseActuelle > 0 && Math.abs(distanceX) <= vitesseActuelle && Math.abs(distanceY) <= vitesseActuelle) {
            this.setX(cibleX);
            this.setY(cibleY);
            etapeActuelle++;
        }
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
    
//     Méthode appelée à la mort de l'ennemi.
//     À Override pour les ennemis qui génèrent d'autres entités en mourant.

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

    public double getCentreX() {
        return this.getX() + TILE_SIZE / 2.0;
    }

    public double getCentreY() {
        return this.getY() + TILE_SIZE / 2.0;
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

//     Applique un effet de statut à cet ennemi pour une durée donnée.
//     Renouvelle la durée si l'effet est déjà actif.
    public void appliqueEffet(Effet effet) {
        switch (effet) {
            case STUN -> {
                this.dureeStunRestante = Math.max(this.dureeStunRestante, 120);
                this.vitesseActuelle = 0;
            }
            case SLOW -> {
                this.dureeSlowRestante = Math.max(this.dureeSlowRestante, 120);
            }
            case BURN -> {
                this.dureeBrulageRestante = Math.max(this.dureeBrulageRestante, 60);
            }
            case SHIELD -> {
                if (!shield) {
                    this.ajouterPv(((int) Math.floor(this.getPv() * 1.2)));
                    shield = true;
                }
            }
            case SPEED_BOOST -> {
                this.dureeSpeedBoostRestante = Math.max(this.dureeSpeedBoostRestante, 150);
            }
        }
    }
}
