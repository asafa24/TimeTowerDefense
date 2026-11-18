package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.geometry.Point2D;

public class Futur extends Epoque {

    public Futur() {
        super(
                new int[][] {
                        { 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 6, 3, 3, 3, 9, 3, 3, 3, 2, 0, 0 },
                        { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                        { 6, 3, 9, 3, 2, 0, 0, 0, 6, 3, 9, 3, 2 },
                        { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
                        { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
                        { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
                        { 5, 3, 8, 3, 4, 0, 0, 0, 5, 3, 8, 3, 4 },
                        { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                        { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }
                },
                new Point2D[] { new Point2D(2, 10), new Point2D(10, 10)/* , new Point2D(2, 10) */ },
                new Point2D(6, 0)

        );
    }

    @Override
    public String toString() {
        return "Futur";
    }

}
