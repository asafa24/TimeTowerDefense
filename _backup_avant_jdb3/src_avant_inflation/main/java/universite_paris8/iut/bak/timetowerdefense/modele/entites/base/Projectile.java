package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class Projectile extends Entite {
    private Ennemi cible;
    private int degats;
    private double vitesse;
    private IntegerProperty rotation;

    public Projectile(double x, double y, Ennemi cible, int degats) {
        super(x, y);
        this.cible = cible;
        this.degats = degats;
        this.vitesse = 5.0;
        rotation = new SimpleIntegerProperty();
    }

    public void deplacer() {
        if (cible == null || cible.estMort()) {
            return;
        }

        double cibleX = cible.getCentreX();
        double cibleY = cible.getCentreY();
        double dx = cibleX - this.getX();
        double dy = cibleY - this.getY();
        double distance = Math.hypot(dx, dy);

        if (distance <= vitesse) {
            this.setX(cibleX);
            this.setY(cibleY);
        } else {
            this.setX(this.getX() + (dx / distance) * vitesse);
            this.setY(this.getY() + (dy / distance) * vitesse);
        }
        setRotation();
    }

    public void appliquerImpact(List<Ennemi> ennemis){
            cible.recevoirDegats(degats);
    }

    public void setRotation(){
        double dx = this.getCible().getX() - this.getX();
        double dy = this.getCible().getY() - this.getY();
        double angleRadians = Math.atan2(dy,dx);
        double angleDegres = Math.toDegrees(angleRadians);
        if (angleDegres < 0){
            angleDegres += 360;
        }
        rotation.set((int) Math.floor(angleDegres));
    }

    public IntegerProperty getRotation(){
        return rotation;
    }


    public boolean aAtteintCible() {
        if (cible == null || cible.estMort()) {
            return true;
        }
        double dx = cible.getCentreX() - this.getX();
        double dy = cible.getCentreY() - this.getY();

        return Math.hypot(dx, dy) <= vitesse;
    }

    public Ennemi getCible() {
        return cible;
    }

    public int getDegats() {
        return degats;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
}
