package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;

import java.util.List;

public class PyramideShooteuse extends Tour {
    private Ennemi cibleActuelle;
    private int tempsFocus;
    private int degatsMax;
    private int degatsBase;
    private int tick = 0;

    public static IntegerProperty cout = new SimpleIntegerProperty(150);


    public PyramideShooteuse(double x, double y) {
        super(cout.get() , x, y, 1, 150, 1);
        this.degatsBase = 1;
        this.degatsMax = 30;
        this.tempsFocus = 0;
    }
//    public PyramideShooteuse(double x, double y) {
//        super(cout, x, y, 1, 150, 1);
//        this.degatsBase = 1;
//        this.degatsMax = 30;
//        this.tempsFocus = 0;
//    }

    @Override
    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles) {

        if (isStunOuPas()) {
            tempsFocus = 0;
            cibleActuelle = null;
            return;
        }

        if (cibleActuelle == null || cibleActuelle.estMort() || horsDePortee(cibleActuelle)) {
            cibleActuelle = trouverCible(ennemis);
            tempsFocus = 0;
        }

        if (cibleActuelle != null && tick%10 == 0) {
            tempsFocus++;
            // 1 dégât toutes les 45 frames
            int degatsCalcules = degatsBase + (tempsFocus / 15);
            if (degatsCalcules > degatsMax) {
                degatsCalcules = degatsMax;
            }
            // Dégâts instant (pas de projectile)
            cibleActuelle.recevoirDegats(degatsCalcules);

            //  Si le rayon est chargé au max, il enflamme la cible
            if (degatsCalcules == degatsMax && tempsFocus % 60 == 0) {
                cibleActuelle.appliqueEffet(Effet.BURN);
            }
        }
        tick++;
    }

    @Override
    public void inflation() {
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    private boolean horsDePortee(Ennemi e) {
        double distance = Math.hypot((getX()*64+32) - e.getCentreX(), (getY()*64+32) - e.getCentreY());
        return distance > this.getPortee();
    }

    public Ennemi getCibleActuelle() {
        return cibleActuelle;
    }

    public static IntegerProperty coutPropertyPyramideShooteuse() {
        return cout;
    }

}