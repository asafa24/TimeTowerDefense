package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.PluieMeteorites;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Compsognathus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Triceratops;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Tyrannosaurus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Velociraptor;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques.FabriqueEnnemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques.FabriqueEnnemiPrehistoire;

public class Prehistoire extends EpoqueSimple {
    private final FabriqueEnnemi fabriqueEnnemi = new FabriqueEnnemiPrehistoire();

    public Prehistoire() {
        super(
                new int[][] {
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0 },
                        { 0, 0, 0, 0, 6, 3, 3, 3, 3, 3, 4, 0, 0 },
                        { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 5, 3, 3, 2, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 5, 3, 3, 2, 0, 0 },
                        { 0, 0, 6, 3, 3, 2, 0, 0, 0, 0, 1, 0, 0 },
                        { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
                        { 0, 0, 1, 0, 0, 5, 3, 2, 0, 0, 1, 0, 0 },
                        { 3, 3, 4, 0, 0, 0, 0, 5, 3, 3, 4, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                },
                new Point2D[] { new Point2D(0, 9) },
                new Point2D(10, 1),
                new int[][] {
                        { 5, 0, 0, 0 }, // vague 1
                        { 10, 0, 0, 0 }, // vague 2
                        { 20, 2, 0, 0 }, // vague 3
                        { 30, 3, 1, 0 }, // vague 4
                        { 40, 4, 2, 0 }, // vague 5
                        { 45, 5, 3, 0 }, // vague 6
                        { 50, 6, 3, 0 }, // vague 7
                        { 55, 7, 4, 0 }, // vague 8
                        { 60, 10, 6, 0 }, // vague 9
                        { 10, 4, 3, 1 } // vague 10 avec le boss (nn c'est toi tié le boss)
                },
                new PluieMeteorites());
    }

    @Override
    public Ennemi ennemi1() {
        return fabriqueEnnemi.creerEnnemi(0, cheminDepartArrivee());
    }

    @Override
    public Ennemi ennemi2() {
        return fabriqueEnnemi.creerEnnemi(1, cheminDepartArrivee());
    }

    @Override
    public Ennemi ennemi3() {
        return fabriqueEnnemi.creerEnnemi(2, cheminDepartArrivee());
    }

    @Override
    public Ennemi ennemi4() {
        return fabriqueEnnemi.creerEnnemi(3, cheminDepartArrivee());
    }

    @Override
    public String toString() {
        return "Prehistoire";
    }

}
