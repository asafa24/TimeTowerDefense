package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Level;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Vague;
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

    @FXML
    private Label labelPvBase;

    @FXML
    private Label labelArgent;

    @FXML
    private Label labelVague;

    private Timeline gameLoop;
    private int temps,delay;


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
        this.jeu = new Jeu();
        this.vueEntite = new EntiteVue(entityPane);
        vueEntite.creerBindings(jeu.getEnnemi());
        vueEntite.creerBindings(jeu.getDefenses());
        vueEntite.creerBindings(jeu.getProjectiles());


        this.vague = new Vague();
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
        vague.test();





        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev -> {
                    if (temps%80 == 0 && !vague.getQueue().isEmpty()) {

                        jeu.addEnnemi(creerEnnemi(route, vague.defiler() ));


                    }
                    else{
                        if (delay > 600 ){
                            delay = 0;
                            vague.vagueSuivante();
                        }
                        else {
                            if (vague.getQueue().isEmpty()){
                                delay++;
                            }

                        }
                    }
                    jeu.tick();
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    @FXML
    public void gererTouches(KeyEvent event) {
        switch (event.getCode()) {
            case DIGIT1, NUMPAD1 -> {
                poseDeTourUn();
            }
            case DIGIT2, NUMPAD2 -> {
                poseDeTourDeux();
            }
            case DIGIT3, NUMPAD3 -> {
                poseDeTourTrois();
            }
            case ESCAPE -> {
                this.typeTourSelectionnee = 0;
                System.out.println("Selection annulée");
            }
        }
    }

    private Ennemi creerEnnemi(List<Point2D> route, int id ) {
        switch (id) {
            case 0 :
                return new Ennemi(0, 64 * 9, 1, 2, 10, route);
            case 1 :
                return new Ennemi(0, 64 * 9, 5, 4, 10, route);
            case 2 :
                return new Ennemi(0, 64 * 9, 15, 1, 10, route);
            default :
                return new Ennemi(0, 64 * 9, 20, 1, 10, route);

        }

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
                this.tourSelectionne = new Tour(25, xGrille, yGrille) ;
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