package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.effets.EffetSpeedBoost;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Boss;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

import java.util.List;

public class Roi extends Boss {
    public Roi(List<Point2D> chemin) {
        super(1500, 1, 200, 180, chemin);
        setX(6*64);
        setY(10*64);
    }

    @Override
    public void competence(List<Ennemi> ennemis, List<Defense> defenses) {
        for(Ennemi e : ennemis){
            e.ajouterEffet(new EffetSpeedBoost());
        }
        System.out.println("Speed Boost activéw");
    }
}
