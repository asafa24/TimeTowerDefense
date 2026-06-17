package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.ArrayList;
import java.util.List;

public class Generateur extends Ennemi {
    public Generateur(List<Point2D> chemin) {
        super(chemin.get(0).getX()*64, chemin.get(0).getY()*64, 500, 1, 50, chemin);
    }

    @Override
    public List<Ennemi> onDeath() {
        List<Ennemi> robots = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Robot robot = new Robot(this.getChemin());
            robot.setX(this.getX() + (i * 20));
            robot.setY(this.getY());
            robot.setEtapeActuelle(this.getEtapeActuelle());
            robots.add(robot);
        }
        return robots;
    }
}
