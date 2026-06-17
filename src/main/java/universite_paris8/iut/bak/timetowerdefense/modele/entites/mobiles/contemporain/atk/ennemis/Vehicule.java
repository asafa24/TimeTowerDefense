package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.ArrayList;
import java.util.List;

public class Vehicule extends Ennemi {
    public Vehicule(List<Point2D> chemin) {
        super(6 * 64, 10 * 64, 150, 3, 50, chemin);
    }


    @Override
    public List<Ennemi> onDeath() {
        List<Ennemi> soldats = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SoldatRouge soldat = new SoldatRouge(this.getChemin());
            soldat.setX(this.getX() + (i % 2 * 32)); // Décalage pour ne pas qu'ils soient tous au même endroit
            soldat.setY(this.getY() + (i / 2 * 32));
            soldat.setEtapeActuelle(this.getEtapeActuelle());
            soldats.add(soldat);
        }
        return soldats;
    }
}
