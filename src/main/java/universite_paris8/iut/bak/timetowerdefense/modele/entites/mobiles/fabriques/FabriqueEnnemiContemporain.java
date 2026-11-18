package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.CharAssaut;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.Dictateur;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.SoldatRouge;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.Vehicule;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

import java.util.List;

public class FabriqueEnnemiContemporain extends FabriqueEnnemi {
    @Override
    public Ennemi creerEnnemi(int type, List<Point2D> route, Jeu jeu) {
        return switch (type) {
            case 0 -> new SoldatRouge(route);
            case 1 -> new Vehicule(route);
            case 2 -> new CharAssaut(route);
            default -> new Dictateur(route);
        };
    }
}
