package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.PluieMeteorites;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.CharAssaut;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.Dictateur;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.SoldatRouge;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.Vehicule;

public class Contemporain extends EpoqueSimple {

    public Contemporain() {
        super(
                new int[][] {
                        { 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 6, 3, 3, 3, 8, 3, 3, 3, 2, 0, 0 },
                        { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                        { 0, 0, 5, 3, 2, 0, 0, 0, 6, 3, 4, 0, 0 },
                        { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0 },
                        { 0, 6, 3, 3, 4, 0, 0, 0, 5, 3, 3, 2, 0 },
                        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                        { 0, 5, 3, 3, 3, 3, 9, 3, 3, 3, 3, 4, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }
                },
                // new Point2D[] { new Point2D(6, 10), new Point2D(6, 10), new Point2D(6, 10),
                // new Point2D(6, 10) },
                new Point2D[] { new Point2D(6, 10) },
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
        return new SoldatRouge(cheminDepartArrivee());

    }

    @Override
    public Ennemi ennemi2() {
        return new Vehicule(cheminDepartArrivee());

    }

    @Override
    public Ennemi ennemi3() {
        return new CharAssaut(cheminDepartArrivee());

    }

    @Override
    public Ennemi ennemi4() {
        return new Dictateur(cheminDepartArrivee());

    }

    @Override
    public String toString() {
        return "Contemporain";
    }

}
