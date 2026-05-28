package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.*;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Level;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Vague;
import universite_paris8.iut.bak.timetowerdefense.vue.EntiteVue;
import universite_paris8.iut.bak.timetowerdefense.vue.TerrainVue;


import java.net.URL;
import javafx.util.Duration;
import universite_paris8.iut.bak.timetowerdefense.vue.UIVue;

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

    @FXML
    private Button tourUn;

    private Timeline gameLoop;
    private int temps, delay;


    private Level level;
    private TerrainVue vueTerrain;
    private EntiteVue vueEntite;
    private UIVue uiVue;
    private Jeu jeu;
    private Tour tourSelectionne;
    private Piege piegeSelectione;

    private int typeDefenseSelectionnee = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.level = new Level();
        this.vueTerrain = new TerrainVue(backgroundPane);
        this.jeu = new Jeu();
        this.vueEntite = new EntiteVue(entityPane);
        this.uiVue = new UIVue();
        tourUn.setGraphic(uiVue.setImageT1());

        vueEntite.creerBindings(jeu.getEnnemi());
        vueEntite.creerBindings(jeu.getDefenses());
        vueEntite.creerBindings(jeu.getProjectiles());


        labelArgent.textProperty().bind(jeu.getSoldeProperty().asString("Solde : %d"));
        labelVague.textProperty().bind(jeu.getVague().getVagueProperty().asString("Vague : %d"));
        labelPvBase.textProperty().bind(jeu.getPvBaseProperty().asString("PV : %d"));


        initAnimation();
        gameLoop.play();

        int[][] donneesMap = level.loadLevel(0);
        vueTerrain.drawMap(donneesMap);
    }

    private void initAnimation() {

        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);


        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev -> {
                    jeu.tick();
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    @FXML
    public void gererTouches(KeyEvent event) {
        switch (event.getCode()) {
            case DIGIT1, NUMPAD1, AMPERSAND -> {
                poseDeTourUn();
            }
            case DIGIT2, NUMPAD2, UNDEFINED -> {
                poseDeTourDeux();
            }
            case DIGIT3, NUMPAD3, QUOTEDBL-> {
                poseDeTourTrois();
            }
            case DIGIT4, NUMPAD4, QUOTE-> {
                poseDeTourQuatre();
            }
            case ESCAPE -> {
                this.typeDefenseSelectionnee = 0;
                System.out.println("Selection annulée");
            }
            default -> System.out.println(event.getCode().getName());
        }
    }


    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) {

        int xGrille = (int) Math.floor(mouseEvent.getX() / 64);
        int yGrille = (int) Math.floor(mouseEvent.getY() / 64);

        if (this.typeDefenseSelectionnee == 0) {
            System.out.println("Veuillez sélectionner une tour d'abord.");
            return;
        }
        switch(typeDefenseSelectionnee){
            case 1 -> {
                this.tourSelectionne = new Tour(50, xGrille, yGrille ,10 ,64  ,60);
                jeu.poserTour(tourSelectionne);
            }

            case 2 -> {
                this.tourSelectionne = new TourCercle(150, xGrille, yGrille,40,128, 150,64);
                jeu.poserTour(tourSelectionne);

            }
            case 3 -> {
                this.tourSelectionne = new TourStun(150, xGrille, yGrille,10,128, 200,180);
                jeu.poserTour(tourSelectionne);
            }
            case 4 -> {
                this.piegeSelectione = new MiniVolcan(25 ,xGrille ,yGrille,5);
                jeu.poserPiege(piegeSelectione);
            }
        }

        this.typeDefenseSelectionnee= 0;
    }


    @FXML
    public void poseDeTourUn() {
        this.typeDefenseSelectionnee = 1;
        System.out.println("Tour numéro un sélectionnée.");
    }

    @FXML
    public void poseDeTourDeux() {
        this.typeDefenseSelectionnee = 2;
        System.out.println("Tour numéro deux sélectionnée.");
    }

    @FXML
    public void poseDeTourTrois() {
        this.typeDefenseSelectionnee = 3;
        System.out.println("Tour numéro trois sélectionnée.");
    }

    public void poseDeTourQuatre( ) {
        this.typeDefenseSelectionnee = 4;
        System.out.println("Tour numéro trois sélectionnée.");
    }
}