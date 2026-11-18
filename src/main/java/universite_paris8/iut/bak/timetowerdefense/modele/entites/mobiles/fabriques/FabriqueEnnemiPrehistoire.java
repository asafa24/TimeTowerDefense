package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Compsognathus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Triceratops;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Tyrannosaurus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Velociraptor;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

import java.util.List;

public class FabriqueEnnemiPrehistoire extends FabriqueEnnemi {
    @Override
    public Ennemi creerEnnemi(int type, List<Point2D> route, Jeu jeu) {
        return switch (type) {
            case 0 -> new Compsognathus(route);
            case 1 -> new Velociraptor(route);
            case 2 -> new Triceratops(route);
            default -> new Tyrannosaurus(route);
        };
    }
}

