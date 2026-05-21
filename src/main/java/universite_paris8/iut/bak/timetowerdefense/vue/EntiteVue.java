package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Entite;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;

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
            ImageView img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/ennemi1.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            img.setScaleX(-1);
            sprite = img;
        }
        if (e instanceof Tour){
            ImageView img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/arbre.png")));
            img.setTranslateX(e.getX()*64);
            img.setTranslateY(e.getY()*64);
            sprite = img;
        }
        if (e instanceof TourCercle) {
            ImageView img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/tour.png")));
            img.setTranslateX(e.getX() * 64);
            img.setTranslateY(e.getY() * 64);
            sprite = img;
        }

        if(e instanceof Projectile){
            Circle c = new Circle(8, Color.DARKGREY);
            c.centerXProperty().bind(e.xProperty().add(8));
            c.centerYProperty().bind(e.yProperty().add(8));
            sprite = c;
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
