package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import java.util.List;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.Ultime;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

public interface Epoque {

    boolean coordonneeValide(int x, int y);

    int valeurCoordonnee(int x, int y);

    int hauteurMap();

    int longueurMap();

    List<Point2D> cheminDepartArrivee();

    Vague getVague();

    Ultime getUltime();

    Ennemi ennemi1();

    Ennemi ennemi2();

    Ennemi ennemi3();

    Ennemi ennemi4();
}
