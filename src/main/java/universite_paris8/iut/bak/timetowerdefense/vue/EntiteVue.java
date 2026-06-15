package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.animation.FadeTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Entite;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.Chacal;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.projectiles.FlechetteEmpoisonne;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.projectiles.Jar;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.GolemSable;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.Sarko;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.Golime;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.Momie;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Fantassin;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Compsognathus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Triceratops;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Tyrannosaurus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Velociraptor;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Caillou;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Filet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Fleche;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.CatapulteJAR;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.PorteDeSable;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.TotemFlechette;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.PyramideShooteuse;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.Archer;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.TourMage;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.ArbreRuste;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.CatapulteCaillou;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.LanceFilet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.MiniVolcan;

import java.util.HashMap;

public class EntiteVue {
    private HashMap<Tour, HBox> affichageEtoiles;
    private Pane entityPane;
    private static int TILE_SIZE = 64;
    private HashMap<Entite, Node> affichageEntite;
    private HashMap<Ennemi, Node> affichageBarre;
    private HashMap<Ennemi, Node> affichageRectangle;
    private HashMap<Ennemi, Node> affichageCapacity;
    private HashMap<Defense, Node> affichageProj;
    public EntiteVue(Pane entityPane){
        this.entityPane = entityPane;
        this.affichageEntite = new HashMap<>();
        this.affichageBarre = new HashMap<>();
        this.affichageRectangle = new HashMap<>();
        this.affichageEtoiles = new HashMap<>();
        this.affichageCapacity = new HashMap<>();
        this.affichageProj = new HashMap<>();


    }


    public void creerSprite(Entite e){
        Node sprite = null;
        Node barre_vie = null;
        Node rectangle_vie = null;
        Node capacity = null;
        Node proj = null;
        ImageView img = null;

        try{

        if (e instanceof Ennemi) {
            DoubleProperty taille = new SimpleDoubleProperty(56);
            ImageView vie = new ImageView(String.valueOf(Application.class.getResource("images/tiles/b_vie.png")));


            // Epoque Prehistoire
            if (e instanceof Compsognathus) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/ennemi/ennemi1.png")));
            }
            if (e instanceof Triceratops) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/ennemi/d02.png")));
                Circle cr = new Circle();
                cr.setFill(Color.CYAN);
                cr.setOpacity(0.5);
                cr.setRadius(128);
                cr.translateXProperty().bind(e.xProperty().add(32));
                cr.translateYProperty().bind(e.yProperty().add(32));
                cr.visibleProperty().bind(((Triceratops) e).getShield());
                capacity = cr;
            }
            if (e instanceof Velociraptor) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/ennemi/d01.png")));
            }
            if (e instanceof Tyrannosaurus) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/ennemi/trex.png")));
            }

            // Epoque Antiquite
            if (e instanceof Momie) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/ennemi/momie6.png")));
            }
            if (e instanceof GolemSable) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/ennemi/golem.png")));
                img.setFitHeight(80);
                img.setFitWidth(80);
            }
            if (e instanceof Chacal){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/ennemi/chakal.png")));
            }
            if (e instanceof Golime){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/ennemi/golime.png")));
            }
            if (e instanceof Sarko){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/ennemi/sarko.png")));
            }

            // Epoque Moyen Age
            if(e instanceof Fantassin){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/moyen-age/ennemi/fantassin.png")));
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
        if (e instanceof Tour) {
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/noTexture.png")));
            if (e instanceof ArbreRuste) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/arbre.png")));
            }
            if (e instanceof CatapulteCaillou) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/catapulteT2.png")));
            }
            if (e instanceof LanceFilet) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/LanceFilet.png")));
            }
            if (e instanceof TotemFlechette){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/tour/piegeFlechette.png")));
            }
            if (e instanceof PyramideShooteuse){
                PyramideShooteuse pyramide = (PyramideShooteuse) e;

                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/tour/piramidJ.png")));

                Rectangle rec = new Rectangle();
                rec.setHeight(6);
                rec.setFill(Color.RED);
                rec.setArcWidth(10);
                rec.setArcHeight(10);

                // 1. Positionner le départ du rectangle au centre de la tour
                // (X de la tour * 64 + 32 pour le centre - la moitié de la hauteur du laser pour le centrer verticalement)
                rec.translateXProperty().bind(pyramide.xProperty().multiply(64).add(32));
                rec.translateYProperty().bind(pyramide.yProperty().multiply(64).add(32 - (rec.getHeight() / 2)));

                // 2. Changer le point de pivot de la rotation pour qu'il soit au début à gauche (0, hauteur/2)
                javafx.scene.transform.Rotate rotation = new javafx.scene.transform.Rotate();
                rotation.setPivotX(0);
                rotation.setPivotY(rec.getHeight() / 2);

                // Lier l'angle de la rotation à la propriété du modèle
                rotation.angleProperty().bind(pyramide.angleProperty());
                rec.getTransforms().add(rotation);

                // 3. Adapter la largeur du rectangle pour qu'elle suive l'ennemi
                rec.widthProperty().bind(pyramide.distanceCibleProperty());

                // 4. Visibilité
                rec.visibleProperty().bind(pyramide.atkProperty());

                proj = rec;
            }
            if (e instanceof CatapulteJAR){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/tour/catapulteJar.png")));
            }
            if (e instanceof Archer){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/moyen-age/def/tour/archer.png")));
            }
            if (e instanceof TourMage){
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/moyen-age/def/tour/tourmage.png")));
                img.setFitWidth(74);
                img.setFitHeight(74);
            }

            img.setTranslateX(e.getX() * 64);
            img.setTranslateY(e.getY() * 64);
            sprite = img;
            actualiserEtoiles((Tour) e);
            ((Tour) e).niveauProperty().addListener((obs, oldVal, newVal) -> {
                actualiserEtoiles((Tour) e);
            });
        }
        if (e instanceof Fleche) {
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/fleche.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            img.rotateProperty().bind(((Projectile) e).getRotation());
            //img.setScaleX(-1);
            sprite = img;
        }
        if (e instanceof Caillou) {
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/pierreT2.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            //img.setScaleX(-1);
            sprite = img;
        }
        if (e instanceof Jar){
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/projectile/jar.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            //img.setScaleX(-1);
            sprite = img;
        }
        if(e instanceof FlechetteEmpoisonne){
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/projectile/flechette.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            img.rotateProperty().bind(((Projectile) e).getRotation());
            //img.setScaleX(-1);
            sprite = img;


        }
        if(e instanceof Filet){
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/filet.png")));
            img.translateXProperty().bind(e.xProperty());
            img.translateYProperty().bind(e.yProperty());
            //img.setScaleX(-1);
            sprite = img;
        }
        if (e instanceof MiniVolcan){
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/volcan.png")));
            img.setTranslateX(e.getX() * 64);
            img.setTranslateY(e.getY() * 64);
            sprite = img;
        }
        if (e instanceof PorteDeSable){
            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/tour/murDeSable.png")));
            img.setTranslateX(e.getX() * 64);
            img.setTranslateY(e.getY() * 64);
            sprite = img;
        }


    }
    catch (Exception v){
        img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/noTexture.png")));
        img.setTranslateX(e.getX() * 64);
        img.setTranslateY(e.getY() * 64);
        sprite = img;

        }
        if (capacity != null){
            entityPane.getChildren().add(capacity);
            affichageCapacity.put((Ennemi) e, capacity);
        }
        if (sprite != null){
            entityPane.getChildren().add(sprite);
            affichageEntite.put(e, sprite);
        }
        if (proj != null){
            entityPane.getChildren().add(proj);
            affichageProj.put((Defense) e, proj);
        }
        if (barre_vie != null){
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
        Node capacity = affichageCapacity.get(e);
        Node proj = affichageProj.get(e);
        if(sprite != null){
            if(!(e instanceof Projectile)) {
                FadeTransition fadeout = new FadeTransition(Duration.seconds(1), sprite);
                fadeout.setToValue(0.0);
                fadeout.setOnFinished(event -> entityPane.getChildren().remove(sprite));
                fadeout.play();
            } else {
                entityPane.getChildren().remove(sprite);
            }
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
        if (proj !=null){
            entityPane.getChildren().remove(proj);
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
