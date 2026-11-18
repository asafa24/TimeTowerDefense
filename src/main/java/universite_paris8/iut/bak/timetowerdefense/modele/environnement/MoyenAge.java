package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.PluieMeteorites;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Cavalier;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Chevalier;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Moine;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Roi;

public class MoyenAge extends EpoqueSimple {

    public MoyenAge() {
        super(
                new int[][] {
                        { 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 3, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }
                },
                new Point2D[] { new Point2D(6, 10), new Point2D(0, 5), new Point2D(12, 5) },
                new Point2D(6, 0),
                new int[][] {
                        { 10, 0, 0, 0 }, // vague 1
                        { 12, 1, 0, 0 }, // vague 2
                        { 15, 2, 0, 0 }, // vague 3
                        { 34, 4, 1, 0 }, // vague 4
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
        return new Moine(cheminDepartArrivee());

    }

    @Override
    public Ennemi ennemi2() {
        return new Chevalier(cheminDepartArrivee());

    }

    @Override
    public Ennemi ennemi3() {
        return new Cavalier(cheminDepartArrivee());

    }

    @Override
    public Ennemi ennemi4() {
        return new Roi(cheminDepartArrivee());

    }

    @Override
    public String toString() {
        return "Moyen-Age";
    }

}
