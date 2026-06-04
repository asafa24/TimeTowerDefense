package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.ArbreRuste;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.CatapulteOs;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.LanceFilet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.ArbreRuste;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Level;
import universite_paris8.iut.bak.timetowerdefense.vue.EntiteVue;
import universite_paris8.iut.bak.timetowerdefense.vue.PreviewVue;
import universite_paris8.iut.bak.timetowerdefense.vue.TerrainVue;

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import universite_paris8.iut.bak.timetowerdefense.vue.UIVue;
import universite_paris8.iut.bak.timetowerdefense.vue.effets.ExplosionVue;

import java.util.ResourceBundle;

public class ControleurJeu implements Initializable {
    @FXML
    private StackPane paneMain ;

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
    private double mouseX, mouseY;

    private Level level;
    private TerrainVue vueTerrain;
    private EntiteVue vueEntite;
    private PreviewVue vuePreview;


    private ExplosionVue vueExplosion;
    private UIVue uiVue;
    private Jeu jeu;
    private Tour tourSelectionne;
    private Piege piegeSelectione;

    private int typeDefenseSelectionnee = 0;

    @FXML
    private Pane zoneStats;
    @FXML
    private Label tourUnArgent;
    @FXML
    private Label tourDeuxArgent;
    @FXML
    private Label tourTroisArgent;
    @FXML
    private Label tourQuatreArgent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.level = new Level();
        this.vueTerrain = new TerrainVue(backgroundPane);
        this.jeu = new Jeu();
        this.vueEntite = new EntiteVue(entityPane);
        this.vuePreview = new PreviewVue(entityPane, -1,jeu.getPreview());
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

        tourUnArgent.textProperty().bind(jeu.getPrixSlotProperty(0).asString());
        tourDeuxArgent.textProperty().bind(jeu.getPrixSlotProperty(1).asString());
        tourTroisArgent.textProperty().bind(jeu.getPrixSlotProperty(2).asString());
        tourQuatreArgent.textProperty().bind(jeu.getPrixSlotProperty(3).asString());





        ultButton.disableProperty().bind(jeu.getCompteurKillProperty().lessThan(100));

        initAnimation();
        gameLoop.play();

        int[][] donneesMap = level.loadLevel(0);
        vueTerrain.drawMap(donneesMap);

        paneMain.setOnMouseMoved(event -> {
            recupererPosition(event);
        });
    }

    private void initAnimation() {

        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);


        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev -> {
                    jeu.tick();
                    if (typeDefenseSelectionnee != 0){
                        jeu.preview(mouseX,mouseY);
                    }

                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void afficherButton(int epoque) {
        tourUn.setGraphic(uiVue.setImageT1(epoque, 0));
        tourDeux.setGraphic(uiVue.setImageT1(epoque, 1));
        tourTrois.setGraphic(uiVue.setImageT1(epoque, 2));
        tourQuatre.setGraphic(uiVue.setImageT1(epoque, 3));
    }

    public void recupererPosition(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @FXML
    public void gererTouches(KeyEvent event) {
        switch (event.getCode()) {
            case DIGIT1, NUMPAD1, AMPERSAND -> {
                vuePreview.remove();
                poseDeTourUn();

            }
            case DIGIT2, NUMPAD2, UNDEFINED -> {
                vuePreview.remove();
                poseDeTourDeux();

            }
            case DIGIT3, NUMPAD3, QUOTEDBL-> {
                vuePreview.remove();
                poseDeTourTrois();

            }
            case DIGIT4, NUMPAD4, QUOTE-> {
                vuePreview.remove();
                poseDeTourQuatre();

            }
            case ESCAPE -> {
                vuePreview.remove();
                this.typeDefenseSelectionnee = 0;
                System.out.println("Selection annulée");
                cacherUI();


            }
            default -> System.out.println(event.getCode().getName());
        }
    }


    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) throws IOException {
        boutonBox.setVisible(true);
        vuePreview.setId(-1);
        vuePreview.remove();

        int xGrille = (int) Math.floor(mouseEvent.getX() / 64);
        int yGrille = (int) Math.floor(mouseEvent.getY() / 64);

        if (this.typeDefenseSelectionnee == 0) {
            boolean tourTrouvee = false;

            for (Defense d : jeu.getDefenses()) {
                if (d instanceof Tour) {
                    if ((int) d.getX() == xGrille && (int) d.getY() == yGrille) {
                        d.setSelectionnee(true);
                        tourTrouvee = true;
                        System.out.println("Tu as sélectionné la tour en position x : "+ d.getX() +" y : "+ d.getY());
                        this.afficherStatsTour((Tour) d);
                    } else {
                        d.setSelectionnee(false);
                    }
                }
            }
            if (!tourTrouvee) {
                this.masquerStatsTour(zoneStats);
                for (Defense d : jeu.getDefenses()) {
                    if (d instanceof Tour) {
                        ((Tour) d).setSelectionnee(false);
                    }
                }
            }
            System.out.println("Veuillez sélectionner une tour d'abord.");
            vuePreview.setId(-1);
            vuePreview.remove();
            return;
        }
        switch(typeDefenseSelectionnee){
            case 1 -> {
                this.piegeSelectione = new MiniVolcan(xGrille ,yGrille);
                jeu.poserPiege(piegeSelectione);
                //this.tourSelectionne.inflation();

            }
            case 2 -> {
                this.tourSelectionne = new ArbreRuste(xGrille, yGrille );
                jeu.poserTour(tourSelectionne);
                this.tourSelectionne.inflation();
            }

            case 3 -> {
                this.tourSelectionne = new CatapulteOs(xGrille, yGrille);
                jeu.poserTour(tourSelectionne);
                this.tourSelectionne.inflation();
            }
            case 4 -> {
                this.tourSelectionne = new LanceFilet(xGrille, yGrille);
                jeu.poserTour(tourSelectionne);
                this.tourSelectionne.inflation();

            }

        }

        this.typeDefenseSelectionnee = 0;
        vuePreview.setId(-1);
        vuePreview.remove();

        this.typeDefenseSelectionnee= 0;
    }

    public void masquerStatsTour(Pane zoneStats) {
        zoneStats.getChildren().clear();
        zoneStats.setVisible(false);
    }

    @FXML
    public void poseDeTourUn() {
        this.typeDefenseSelectionnee = 1;
        boutonBox.setVisible(false);
        System.out.println("Défense numéro un sélectionnée.");
        vuePreview.setId(1);
        jeu.setId(1);
        vuePreview.preview();



    }

    @FXML
    public void poseDeTourDeux() {
        this.typeDefenseSelectionnee = 2;
        boutonBox.setVisible(false);
        System.out.println("Défense numéro deux sélectionnée.");
        vuePreview.setId(2);
        jeu.setId(2);
        vuePreview.preview();

    }

    @FXML
    public void poseDeTourTrois() {
        this.typeDefenseSelectionnee = 3;
        boutonBox.setVisible(false);
        System.out.println("Défense numéro trois sélectionnée.");
        vuePreview.setId(3);
        jeu.setId(3);
        vuePreview.preview();

    }
    // et houi j'ai un passion pour bethoveen étonnant non ? hein bach bach ???

    public void poseDeTourQuatre() {
        this.typeDefenseSelectionnee = 4;
        boutonBox.setVisible(false);
        System.out.println("Défense numéro quatre sélectionnée.");
        vuePreview.setId(4);
        jeu.setId(4);
        vuePreview.preview();
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
    private void afficherStatsTour(Tour tour) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("properties.fxml"));
            Pane carteState = fxmlLoader.load();

            PropertiController controller = fxmlLoader.getController();
            controller.updateStats(tour, this.jeu);

            zoneStats.getChildren().setAll(carteState);
            zoneStats.setVisible(true);
    }


}