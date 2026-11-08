package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.Chacal;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.GolemSable;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.Momie;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.Sarko;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

import java.util.List;

public class FabriqueEnnemiAntiquite extends FabriqueEnnemi {
    @Override
    public Ennemi creerEnnemi(int type, List<Point2D> route, Jeu jeu) {
        return switch (type) {
            case 0 -> new Momie(route);
            case 1 -> new Chacal(route);
            case 2 -> new GolemSable(route);
            default -> new Sarko(route, jeu);
        };
    }
}
