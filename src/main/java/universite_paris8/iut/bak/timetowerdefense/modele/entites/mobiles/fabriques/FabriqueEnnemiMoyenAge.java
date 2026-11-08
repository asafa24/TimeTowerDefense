package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Cavalier;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Chevalier;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Moine;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Roi;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

import java.util.List;

public class FabriqueEnnemiMoyenAge extends FabriqueEnnemi {
    @Override
    public Ennemi creerEnnemi(int type, List<Point2D> route, Jeu jeu) {
        return switch (type) {
            case 0 -> new Moine(route);
            case 1 -> new Chevalier(route);
            case 2 -> new Cavalier(route);
            default -> new Roi(route);
        };
    }
}
