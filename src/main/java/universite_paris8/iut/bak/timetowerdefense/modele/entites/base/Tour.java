package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Tour extends Defense {
    private int degats;
    private IntegerProperty portee;
    private double cadence;
    private double compteurTir;

    private boolean isStun = false;
    private IntegerProperty niveau;

    private BooleanProperty actif = new SimpleBooleanProperty(true);
    private int dureeStun = 0;

    public Tour(int cout, double x, double y) {
        super(cout, x, y);
        this.degats = 25;
        this.portee = new SimpleIntegerProperty(100);
        this.cadence = 120;
        this.compteurTir = cadence / 2;
        this.niveau = new SimpleIntegerProperty(0);
    }

    public Tour(int cout, double x, double y, int degats, int portee, int cadence) {
        super(cout, x, y);
        this.degats = degats;
        this.portee = new SimpleIntegerProperty(portee);
        this.cadence = cadence;
        this.compteurTir = cadence - 10;
        this.niveau = new SimpleIntegerProperty(0);
    }

    public abstract void agir(List<Ennemi> ennemis, List<Projectile> projectiles);


     public Ennemi trouverCible(List<Ennemi> ennemis) {
        Ennemi ciblePlusAvancee = null;
        int etapeMax = -1;
        double centreTx = getX() * 64 + 32;
        double centreTy = getY() * 64 + 32;

        for (Ennemi e : ennemis) {
            double distance = Math.hypot(centreTx - e.getCentreX(), centreTy - e.getCentreY());
            if (distance <= portee.get()) {
                if (e.getEtapeActuelle() > etapeMax) {
                    etapeMax = e.getEtapeActuelle();
                    ciblePlusAvancee = e;
                }
            }
        }
        return ciblePlusAvancee;
    }

//     Améliore les statistiques de la tour (dégâts, cadence, portée)
//     et augmente son coût pour la prochaine amélioration.
    public void amelioration() {
        this.niveau.set(this.niveau.get() + 1);
        this.degats = (int) (this.degats * 1.2);
        this.cadence = this.cadence / 1.10;
        this.portee.set(this.portee.get() + 20);
        super.setCout((int) (this.getCout() * 1.30));
    }

    public abstract void inflation();

    public boolean isStunOuPas() {
        if (this.dureeStun > 0) {
            this.dureeStun--;
            if (getDureeStun() <= 0) {
                this.isStun = false;
            }
        }
        return isStun;
    }

    public double getCadence() {
        return cadence;
    }

    public double getCompteurTir() {
        return compteurTir;
    }

    public int getDegats() {
        return degats;
    }

    public int getPortee() {
        return portee.get();
    }

    public IntegerProperty porteeProperty() {
        return portee;
    }

    public void setCompteurTir(double compteurTir) {
        this.compteurTir = compteurTir;
    }

    public void setStun(int dureeStun) {
        isStun = true;
        this.dureeStun = dureeStun;
    }

    public IntegerProperty niveauProperty() {
        return niveau;
    }

    public int getNiveau() {
        return niveau.get();
    }

    public void setDureeStun(int dureeStun) {
        this.dureeStun = dureeStun;
    }

    public void modifStun(Boolean stun) {
        this.isStun = stun;
    }

    public int getDureeStun() {
        return dureeStun;
    }

    public boolean getActif() {
        return actif.get();
    }

    public void desactiver() {
        this.actif.set(false);
    }

    public void activer() {
        this.actif.set(true);
    }

    public BooleanProperty getActifProperty() {
        return actif;
    }
}
