package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;

import java.util.List;

public class PyramideShooteuse extends Tour {
    private static final int PRIX_BASE = 140;

    private Ennemi cibleActuelle;
    private int tempsFocus;
    private int degatsMax;
    private int degatsBase;
    private int tick = 0;
    private IntegerProperty angle = new SimpleIntegerProperty(0);
    private BooleanProperty atk = new SimpleBooleanProperty(false);
    private javafx.beans.property.DoubleProperty distanceCible = new javafx.beans.property.SimpleDoubleProperty(0);

    public PyramideShooteuse(double x, double y) {
        super(PRIX_BASE, x, y, 1, 150, 1);
        this.degatsBase = 1;
        this.degatsMax = 30;
        this.tempsFocus = 0;
    }

    public static int getPrixBase() {
        return PRIX_BASE;
    }

    public void setAngle() {
        double tourX = this.getX() * 64 + 32;
        double tourY = this.getY() * 64 + 32;
        double cibleX = cibleActuelle.getCentreX();
        double cibleY = cibleActuelle.getCentreY();

        double dx = cibleX - tourX;
        double dy = cibleY - tourY;

        double angleRadians = Math.atan2(dy, dx);
        double angleDegres = Math.toDegrees(angleRadians);
        if (angleDegres < 0){
            angleDegres += 360;
        }
        angle.set((int) Math.floor(angleDegres));

        distanceCible.set(Math.hypot(dx, dy));
    }

    public javafx.beans.property.DoubleProperty distanceCibleProperty() {
        return distanceCible;
    }

    @Override
    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles) {
        if(!getActif()) return;

        if (isStunOuPas()) {
            tempsFocus = 0;
            cibleActuelle = null;
            return;
        }

        if (cibleActuelle == null || cibleActuelle.estMort() || horsDePortee(cibleActuelle)) {
            cibleActuelle = trouverCible(ennemis);
            tempsFocus = 0;
            setAtk(false);
        }
        else{
            setAtk(true);
            setAngle();
        }

        if (cibleActuelle != null && tick%10 == 0) {
            tempsFocus++;
            int degatsCalcules = degatsBase + (tempsFocus / 15);
            if (degatsCalcules > degatsMax) {
                degatsCalcules = degatsMax;
            }
            cibleActuelle.recevoirDegats(degatsCalcules);

            if (degatsCalcules == degatsMax && tempsFocus % 60 == 0) {
                cibleActuelle.appliqueEffet(Effet.BURN);
            }
        }
        tick++;
    }

    private boolean horsDePortee(Ennemi e) {
        double distance = Math.hypot((getX()*64+32) - e.getCentreX(), (getY()*64+32) - e.getCentreY());
        return distance > this.getPortee();
    }

    public Ennemi getCibleActuelle() {
        return cibleActuelle;
    }

    public IntegerProperty angleProperty() {
        return angle;
    }

    public BooleanProperty atkProperty() {
        return atk;
    }

    public void setAtk(boolean atk) {
        this.atk.set(atk);
    }
    @Override
    public void inflation() {
        this.setCout((int) Math.floor(this.getCout() * 1.1));
    }
}





















