package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.geometry.Point2D;

public class Antiquite extends Epoque {

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
                new Point2D(0, 2));
    }

    @Override
    public String toString() {
        return "Antiquite";
    }

}
