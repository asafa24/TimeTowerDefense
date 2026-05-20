package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.Entite;
import universite_paris8.iut.bak.timetowerdefense.modele.Tour;

import java.util.HashMap;

public class EntiteVue {
    private Pane entityPane;
    private static int TILE_SIZE = 64;
    private HashMap<Entite, Node> affichageEntite;

    public EntiteVue(Pane entityPane){
        this.entityPane = entityPane;
        this.affichageEntite = new HashMap<>();
    }

    public void creerSprite(Entite e){
        Node sprite = null;

        if (e instanceof Ennemi){
            Rectangle r = new Rectangle(32, 32, Color.DARKRED);
            r.translateXProperty().bind(e.xProperty().add(16));
            r.translateYProperty().bind(e.yProperty().add(16));
            sprite = r;
        }

        if (e instanceof Tour){
            ImageView img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/arbre.png")));
            img.translateXProperty().bind(e.xProperty().multiply(64));
            img.translateYProperty().bind(e.yProperty().multiply(64));
            sprite = img;
        }

        if (sprite != null){
            entityPane.getChildren().add(sprite);
            affichageEntite.put(e, sprite);
        }
    }

    public void supprimerSprite(Entite e){
        Node sprite = affichageEntite.get(e);
        if(sprite != null){
            entityPane.getChildren().remove(sprite);
        }
    }

    public void creerBindings(ObservableList<? extends Entite> liste){
        liste.addListener((ListChangeListener<Entite>) ch -> {
            while(ch.next()){
                if(ch.wasAdded()){
                    for(Entite e : ch.getAddedSubList()) creerSprite(e);
                }

                if (ch.wasRemoved()){
                    for(Entite e : ch.getRemoved()) supprimerSprite(e);
                }
            }
        });
    }


}
