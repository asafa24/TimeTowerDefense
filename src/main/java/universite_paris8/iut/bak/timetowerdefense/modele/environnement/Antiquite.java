package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.TempeteDeSable;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.Chacal;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.GolemSable;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.Momie;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.Sarko;

public class Antiquite extends EpoqueSimple {

    public Antiquite() {
        super(
                new int[][] {
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 6, 3, 2, 0, 0, 0, 0, 0, 0, 0 },
                        { 7, 2, 0, 1, 0, 5, 3, 3, 3, 3, 2, 0, 0 },
                        { 0, 5, 3, 4, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                        { 0, 0, 6, 3, 3, 3, 3, 3, 3, 3, 4, 0, 0 },
                        { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 1, 0, 6, 3, 3, 2, 0, 0, 0, 0, 0 },
                        { 0, 0, 5, 3, 4, 0, 0, 5, 3, 3, 2, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }
                },
                new Point2D[] { new Point2D(10, 10) },
                new Point2D(0, 2),
                new int[][] {
                        { 7, 0, 0, 0 }, // vague 1
                        { 12, 1, 0, 0 }, // vague 2
                        { 14, 2, 0, 0 }, // vague 3
                        { 34, 4, 1, 0 }, // vague 4
                        { 40, 4, 2, 0 }, // vague 5
                        { 45, 5, 3, 0 }, // vague 6
                        { 50, 6, 3, 0 }, // vague 7
                        { 55, 7, 4, 0 }, // vague 8
                        { 60, 10, 6, 0 }, // vague 9
                        { 10, 4, 3, 1 } // vague 10 avec le boss (nn c'est toi tié le boss)
                },
                new TempeteDeSable());
    }

    @Override
    public Ennemi ennemi1() {
        return new Momie(cheminDepartArrivee());
    }

    @Override
    public Ennemi ennemi2() {
        return new Chacal(cheminDepartArrivee());
    }

    @Override
    public Ennemi ennemi3() {
        return new GolemSable(cheminDepartArrivee());
    }

    @Override
    public Ennemi ennemi4() {
        return new Sarko(cheminDepartArrivee());

    }

    @Override
    public String toString() {
        return "Antiquite";
    }

}
