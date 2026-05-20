package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import universite_paris8.iut.bak.timetowerdefense.modele.Ennemi;

import java.util.HashMap;

public class EntiteVue {
    private Pane entityPane;
    private static int TILE_SIZE = 64;
    private HashMap<Ennemi, Node> affichageEnnemi;

    public EntiteVue(Pane entityPane){
        this.entityPane = entityPane;
    }


}
