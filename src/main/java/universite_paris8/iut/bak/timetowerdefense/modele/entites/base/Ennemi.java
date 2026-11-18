package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.effets.EffetStatut;
import java.util.EnumMap;
import java.util.Map;

import java.util.Collections;
import java.util.List;

public class Ennemi extends Entite implements Destructible {
    private int pv, pvMax;
    private DoubleProperty pvProp;
    private IntegerProperty direction;
    private double vitesseBase;
    private double vitesseActuelle;
    private int recompense;

    private final Map<Effet, Integer> effetsActifs = new EnumMap<>(Effet.class);

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
        this.vitesseActuelle = appliquerEffets();
        if (this.vitesseActuelle == 0) return;

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
    private double appliquerEffets() {
        double vitesse = this.vitesseBase;
        for (Map.Entry<Effet, Integer> entree : effetsActifs.entrySet()) {
            int restant = entree.getValue() - 1;
            entree.setValue(restant);
            EffetStatut strategie = entree.getKey().getStrategie();
            strategie.tick(this, restant);
            vitesse = strategie.modifierVitesse(vitesse);
        }
        effetsActifs.values().removeIf(duree -> duree <= 0);
        return vitesse;
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
    EffetStatut strategie = effet.getStrategie();
    if (effetsActifs.containsKey(effet)) {
        effetsActifs.merge(effet, strategie.getDuree(), Math::max);
    } else {
        effetsActifs.put(effet, strategie.getDuree());
        strategie.surApplication(this);
    }
}
}
