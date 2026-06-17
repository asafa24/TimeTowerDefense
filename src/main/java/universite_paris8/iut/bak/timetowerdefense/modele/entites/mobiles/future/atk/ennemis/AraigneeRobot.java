package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.ArrayList;
import java.util.List;

public class AraigneeRobot extends Ennemi {
    public AraigneeRobot(List<Point2D> chemin) {
        super(chemin.get(0).getX()*64, chemin.get(0).getY()*64, 300, 3, 30, chemin);
    }

    @Override
    public List<Ennemi> onDeath() {
        List<Ennemi> petitesAraignees = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Robot petiteAraignee = new Robot(this.getChemin());
            petiteAraignee.setX(this.getX() + (i * 20));
            petiteAraignee.setY(this.getY());
            petiteAraignee.setEtapeActuelle(this.getEtapeActuelle());
            petitesAraignees.add(petiteAraignee);
        }
        return petitesAraignees;
    }
}
