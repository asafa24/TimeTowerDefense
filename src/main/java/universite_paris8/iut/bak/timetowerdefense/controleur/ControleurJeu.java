package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.*;
import universite_paris8.iut.bak.timetowerdefense.vue.EntiteVue;
import universite_paris8.iut.bak.timetowerdefense.vue.TerrainVue;

import java.net.URL;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControleurJeu implements Initializable {

    @FXML
    private Pane backgroundPane;
    @FXML
    private Pane entityPane;


    private Timeline gameLoop;
    private int temps;

    private Level level;
    private Vague vague;
    private TerrainVue vueTerrain;
    private EntiteVue vueEntite;
    private Jeu jeu;
    private Tour tourSelectionne;
    private int typeTourSelectionnee = 0;
    int[][] emplacementTour;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.level = new Level();
        this.vueTerrain = new TerrainVue(backgroundPane);
        this.vueEntite = new EntiteVue(entityPane);

        this.vague = new Vague();
        this.jeu = new Jeu();
        this.emplacementTour = new int[11][13];
        vague.test();
        initAnimation();
        gameLoop.play();

        int[][] donneesMap = level.loadLevel(0);
        vueTerrain.drawMap(donneesMap);
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        List<Point2D> route = level.calculerChemin(0, new Point2D(0, 9), new Point2D(10, 1));
        System.out.println("Chemin trouvé : " + route);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev -> {
                    if(!jeu.perdu()) {
                        if (temps % 80 == 0 && !vague.getQueue().isEmpty()) {
                            jeu.addEnnemi(creerEnnemi(route, couleur(vague.defiler())));
                        }
                        jeu.tick();
                        temps++;
                    }
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    private Ennemi creerEnnemi(List<Point2D> route, Color couleur) {
        Ennemi e = new Ennemi(0, 64 * 9, 50, 2, 10, route);
        Rectangle r = new Rectangle(32, 32, couleur);
        r.translateXProperty().bind(e.xProperty().add(16));
        r.translateYProperty().bind(e.yProperty().add(16));

        r.setOpacity(0.80);
        entityPane.getChildren().add(r);

        return e;
    }
    private Color couleur(int nb ) {
        switch (nb) {
            case 0:
                return Color.RED;
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.GREY;
            default:
                return Color.BLACK;
        }
    }



    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) {

        int xGrille = (int) Math.floor(mouseEvent.getX() / 64);
        int yGrille = (int) Math.floor(mouseEvent.getY() / 64);

        if (this.typeTourSelectionnee == 0) {
            System.out.println("Veuillez sélectionner une tour d'abord.");
            return;
        }
        switch(typeTourSelectionnee){
            case 1 -> {
                this.tourSelectionne = new Tour(500 , xGrille,yGrille ) ;
                jeu.poserTour( tourSelectionne );
            }

            case 2, 3 -> {
                this.tourSelectionne = new Tour(50, xGrille, yGrille);
                jeu.poserTour(tourSelectionne);

            }
        }

        this.typeTourSelectionnee = 0;
    }


    @FXML
    public void poseDeTourUn() {
        this.typeTourSelectionnee = 1;
        System.out.println("Tour numéro un sélectionnée.");
    }

    @FXML
    public void poseDeTourDeux() {
        this.typeTourSelectionnee = 2;
        System.out.println("Tour numéro deux sélectionnée.");
    }

    @FXML
    public void poseDeTourTrois() {
        this.typeTourSelectionnee = 3;
        System.out.println("Tour numéro trois sélectionnée.");
    }

}


