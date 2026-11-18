package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

import java.util.List;

// Créateur abstrait du pattern Factory Method pour les ennemis d'une époque.
public abstract class FabriqueEnnemi {
    public abstract Ennemi creerEnnemi(int type, List<Point2D> route, Jeu jeu);
}
