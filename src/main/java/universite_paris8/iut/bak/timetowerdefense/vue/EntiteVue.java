package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Entite;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.Compsognathus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.Triceratops;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.Velociraptor;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.ArbreRuste;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.CatapulteOs;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.LanceFilet;

import java.util.HashMap;

public class EntiteVue {
    private HashMap<Tour, HBox> affichageEtoiles;
    private Pane entityPane;
    private static int TILE_SIZE = 64;
    private HashMap<Entite, Node> affichageEntite;
    private HashMap<Ennemi, Node> affichageBarre;
    private HashMap<Ennemi, Node> affichageRectangle;
    private HashMap<Ennemi, Node> affichageCapacity;
    public EntiteVue(Pane entityPane){
        this.entityPane = entityPane;
        this.affichageEntite = new HashMap<>();
        this.affichageBarre = new HashMap<>();
        this.affichageRectangle = new HashMap<>();
        this.affichageEtoiles = new HashMap<>();
        this.affichageCapacity = new HashMap<>();

    }


    public void creerSprite(Entite e){
        Node sprite = null;
        Node barre_vie = null;
        Node rectangle_vie = null;
        Node capacity = null;
        ImageView img;


        if (e instanceof Ennemi){
            DoubleProperty taille = new SimpleDoubleProperty(56);
            ImageView vie = new ImageView(String.valueOf(Application.class.getResource("images/tiles/b_vie.png")));
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/ennemi/ennemi1.png")));
            if(e instanceof Compsognathus){
                 img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/ennemi/ennemi1.png")));
            }
            if(e instanceof Triceratops){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/ennemi/d02.png")));
                Circle cr = new Circle();
                cr.setFill(Color.CYAN);
                cr.setOpacity(0.5);
                cr.setRadius(128);
                cr.translateXProperty().bind(e.xProperty().add(32));
                cr.translateYProperty().bind(e.yProperty().add(32));
                cr.visibleProperty().bind( ((Triceratops) e).getShield() );
                capacity = cr ;
            }
            if(e instanceof Velociraptor){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/ennemi/d01.png")));
            }
            if(e instanceof Tyrannosaurus){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/ennemi/trex.png")));
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
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/arbre.png")));
            if(e instanceof ArbreRuste){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/arbre.png")));
            }
            if(e instanceof CatapulteOs){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/catapulteT2.png")));
            }if(e instanceof LanceFilet){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/LanceFilet.png")));
            }
            img.setTranslateX(e.getX()*64);
            img.setTranslateY(e.getY()*64);
            sprite = img;
            actualiserEtoiles((Tour) e);
            ((Tour) e).niveauProperty().addListener((obs, oldVal, newVal) -> {
                actualiserEtoiles((Tour) e);
            });
        }
        if(e instanceof Projectile){
             img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/fleche.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            //img.setScaleX(-1);
            sprite = img;
        }
        if(e instanceof ProjectileCercle){
             img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/pierreT2.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            //img.setScaleX(-1);
            sprite = img;
        }
        if(e instanceof ProjectileStun){
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/filet.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            //img.setScaleX(-1);
            sprite = img;
        }
        if (e instanceof Piege){
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/volcan.png")));
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
        if (capacity != null){
            entityPane.getChildren().add(capacity);
            affichageCapacity.put((Ennemi) e, capacity);
        }

    }

    public void supprimerSprite(Entite e){
        Node sprite = affichageEntite.get(e);
        Node barre_vie = affichageBarre.get(e);
        Node rectangle_vie = affichageRectangle.get(e);
        Node capacity = affichageCapacity.get(e);
        if(sprite != null){
            entityPane.getChildren().remove(sprite);
        }
        if (barre_vie != null){
            entityPane.getChildren().remove(barre_vie);
        }
        if (rectangle_vie != null){
            entityPane.getChildren().remove(rectangle_vie);
        }
        if (capacity !=null){
            entityPane.getChildren().remove(capacity);
        }
        if (e instanceof Tour) {
            HBox etoiles = affichageEtoiles.remove((Tour) e);
            if (etoiles != null) entityPane.getChildren().remove(etoiles);
        }
    }
    public void actualiserEtoiles(Tour tour) {
        if (affichageEtoiles.containsKey(tour)) {
            entityPane.getChildren().remove(affichageEtoiles.get(tour));
            affichageEtoiles.remove(tour);
        }
        if (tour.getNiveau() > 0) {
            HBox boiteEtoiles = new HBox(2);
            boiteEtoiles.setTranslateX((tour.getX() * 64) + 8);
            boiteEtoiles.setTranslateY((tour.getY() * 64) - 10);
            for (int i = 0; i < tour.getNiveau(); i++) {
                Image imgEtoile = new Image(String.valueOf(Application.class.getResource("images/tiles/prehistoire/etoiles.png")));
                ImageView etoileVue = new ImageView(imgEtoile);
                etoileVue.setFitWidth(16); etoileVue.setFitHeight(16); etoileVue.setPreserveRatio(true);
                boiteEtoiles.getChildren().add(etoileVue);
            }
            entityPane.getChildren().add(boiteEtoiles);
            affichageEtoiles.put(tour, boiteEtoiles);
        }

    }
}
