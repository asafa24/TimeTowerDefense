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
import javafx.scene.layout.HBox;
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
import universite_paris8.iut.bak.timetowerdefense.vue.effets.ExplosionVue;

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
    @FXML
    private Button tourDeux;
    @FXML
    private Button tourTrois;
    @FXML
    private Button tourQuatre;
    @FXML
    private HBox boutonBox;

    @FXML
    private Button ultButton;
    @FXML
    private Label labelCompteurKill;

    private Timeline gameLoop;
    private int temps, delay;


    private Level level;
    private TerrainVue vueTerrain;
    private EntiteVue vueEntite;
    private ExplosionVue vueExplosion;
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
        EcouteEntite ecouteEntite = new EcouteEntite(vueEntite);
        this.uiVue = new UIVue();
        afficherButton(0);

        jeu.getEnnemi().addListener(ecouteEntite);
        jeu.getDefenses().addListener(ecouteEntite);
        jeu.getProjectiles().addListener(ecouteEntite);

        labelArgent.textProperty().bind(jeu.getSoldeProperty().asString("Solde : %d"));
        labelVague.textProperty().bind(jeu.getVague().getVagueProperty().asString("Vague : %d"));
        labelPvBase.textProperty().bind(jeu.getPvBaseProperty().asString("PV : %d"));
        labelCompteurKill.textProperty().bind(jeu.getCompteurKillProperty().asString("Kill : %d"));

        ultButton.disableProperty().bind(jeu.getCompteurKillProperty().lessThan(100));

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
    public void afficherButton(int epoque){

        tourUn.setGraphic(uiVue.setImageT1(epoque,1));
        tourDeux.setGraphic(uiVue.setImageT1(epoque,2));
        tourTrois.setGraphic(uiVue.setImageT1(epoque,3));
        tourQuatre.setGraphic(uiVue.setImageT1(epoque,0));


    }

    @FXML
    public void gererTouches(KeyEvent event) {
        switch (event.getCode()) {
            case DIGIT1, NUMPAD1, AMPERSAND -> {
                poseDeTourQuatre();
            }
            case DIGIT2, NUMPAD2, UNDEFINED -> {
                poseDeTourUn();
            }
            case DIGIT3, NUMPAD3, QUOTEDBL-> {
                poseDeTourDeux();
            }
            case DIGIT4, NUMPAD4, QUOTE-> {
                poseDeTourTrois();
            }
            case ESCAPE -> {
                this.typeDefenseSelectionnee = 0;
                System.out.println("Selection annulée");
                cacherUI();


            }
            default -> System.out.println(event.getCode().getName());
        }
    }


    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) {
        boutonBox.setVisible(true);

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
                this.piegeSelectione = new MiniVolcan(25 ,xGrille ,yGrille,5 ,5);
                jeu.poserPiege(piegeSelectione);
            }
        }

        this.typeDefenseSelectionnee= 0;
    }



    @FXML
    public void poseDeTourUn() {
        this.typeDefenseSelectionnee = 1;
        boutonBox.setVisible(false);
        System.out.println("Défense numéro un sélectionnée.");
    }

    @FXML
    public void poseDeTourDeux() {
        this.typeDefenseSelectionnee = 2;
        boutonBox.setVisible(false);
        System.out.println("Défense numéro deux sélectionnée.");
    }

    @FXML
    public void poseDeTourTrois() {
        this.typeDefenseSelectionnee = 3;
        boutonBox.setVisible(false);
        System.out.println("Défense numéro trois sélectionnée.");
    }
    // et houi j'ai un passion pour bethoveen étonnant non ? hein bach bach ???

    public void poseDeTourQuatre( ) {
        this.typeDefenseSelectionnee = 4;
        boutonBox.setVisible(false);
        System.out.println("Défense numéro quatre sélectionnée.");
    }

    public void lancerUltime(){
        jeu.activerUltime();
    }

    @FXML
    public void cacherUI(){
        if(boutonBox.isDisabled()){
            boutonBox.setVisible(true);
        }
        else{
            boutonBox.setVisible(false);
        }
    }
}