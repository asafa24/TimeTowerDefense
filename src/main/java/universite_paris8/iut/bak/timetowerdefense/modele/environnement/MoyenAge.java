package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.geometry.Point2D;

public class MoyenAge extends Epoque {

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
                new Point2D(6, 0));
    }

    @Override
    public String toString() {
        return "Moyen-Age";
    }

}
