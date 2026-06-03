package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

public class Projectile extends Entite {
    private Ennemi cible;
    private int degats;
    private double vitesse;

    public Projectile(double x, double y, Ennemi cible, int degats) {
        super(x, y);
        this.cible = cible;
        this.degats = degats;
        this.vitesse = 5.0;
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
}
