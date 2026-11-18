package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis.AraigneeRobot;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis.Generateur;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis.IA;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis.Robot;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

import java.util.List;

public class FabriqueEnnemiFutur extends FabriqueEnnemi {
    @Override
    public Ennemi creerEnnemi(int type, List<Point2D> route, Jeu jeu) {
        return switch (type) {
            case 0 -> new Robot(route);
            case 1 -> new AraigneeRobot(route);
            case 2 -> new Generateur(route);
            default -> new IA(route);
        };
    }
}
