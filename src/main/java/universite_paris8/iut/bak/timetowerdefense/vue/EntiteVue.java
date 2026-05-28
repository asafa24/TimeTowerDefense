package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Entite;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.*;

import java.util.HashMap;

public class EntiteVue {
    private Pane entityPane;
    private static int TILE_SIZE = 64;
    private HashMap<Entite, Node> affichageEntite;
    private HashMap<Ennemi, Node> affichageBarre;
    private HashMap<Ennemi, Node> affichageRectangle;
    public EntiteVue(Pane entityPane){
        this.entityPane = entityPane;
        this.affichageEntite = new HashMap<>();
        this.affichageBarre = new HashMap<>();
        this.affichageRectangle = new HashMap<>();
    }


    public void creerSprite(Entite e){
        Node sprite = null;
        Node barre_vie = null;
        Node rectangle_vie = null;
        ImageView img;

        if (e instanceof Ennemi){
            DoubleProperty taille = new SimpleDoubleProperty(56);
            ImageView vie = new ImageView(String.valueOf(Application.class.getResource("images/tiles/b_vie.png")));
            if (e instanceof Velociraptor){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/d01.png")));
            }
            else if (e instanceof Triceratops){
                img = img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/d02.png")));
            }
            else {
                 img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/ennemi1.png")));
            }


            Rectangle rec = new Rectangle();
            rec.setWidth(56);
            rec.setHeight(4);
            rec.setFill(Color.GREEN);


            rec.translateXProperty().bind(e.xProperty().add(4));
            rec.translateYProperty().bind(e.yProperty().add(-4));
            rec.widthProperty().bind(taille.multiply(((Ennemi) e).getPvPropProperty()).divide(((Ennemi) e).getPv()));

            vie.translateXProperty().bind(e.xProperty());
            vie.translateYProperty().bind(e.yProperty().add(-20));



            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            img.scaleXProperty().bind(((Ennemi) e).getDirectionProperty());

            rectangle_vie = rec;
            sprite = img;
            barre_vie = vie;
        }
        if (e instanceof Tour){
             img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/arbre.png")));
            img.setTranslateX(e.getX()*64);
            img.setTranslateY(e.getY()*64);
            sprite = img;
        }
        if (e instanceof TourCercle) {
             img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/catapulteT2.png")));
            img.setTranslateX(e.getX() * 64);
            img.setTranslateY(e.getY() * 64);
            sprite = img;
        }
        if (e instanceof TourStun) {
             img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/LanceFilet.png")));
            img.setTranslateX(e.getX() * 64);
            img.setTranslateY(e.getY() * 64);
            sprite = img;
        }

        if(e instanceof Projectile){
             img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/fleche.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            img.setScaleX(-1);
            sprite = img;
        }
        if(e instanceof ProjectileCercle){
             img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/pierreT2.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            img.setScaleX(-1);
            sprite = img;
        }
        if(e instanceof ProjectileStun){
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/filet.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            img.setScaleX(-1);
            sprite = img;
        }
        if (e instanceof MiniVolcan){
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/volcan.png")));
            img.setTranslateX(e.getX() * 64);
            img.setTranslateY(e.getY() * 64);
            sprite = img;

        }

        if (sprite != null){
            entityPane.getChildren().add(sprite);
            affichageEntite.put(e, sprite);
        }
        if (barre_vie != null  ){
            entityPane.getChildren().add(barre_vie);

            affichageBarre.put((Ennemi) e, barre_vie);



        }
        if (rectangle_vie != null){
            entityPane.getChildren().add(rectangle_vie);
            affichageRectangle.put((Ennemi) e, rectangle_vie);
        }
    }

    public void supprimerSprite(Entite e){
        Node sprite = affichageEntite.get(e);
        Node barre_vie = affichageBarre.get(e);
        Node rectangle_vie = affichageRectangle.get(e);
        if(sprite != null){
            entityPane.getChildren().remove(sprite);
        }
        if (barre_vie != null){
            entityPane.getChildren().remove(barre_vie);
        }
        if (rectangle_vie != null){
            entityPane.getChildren().remove(rectangle_vie);
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
