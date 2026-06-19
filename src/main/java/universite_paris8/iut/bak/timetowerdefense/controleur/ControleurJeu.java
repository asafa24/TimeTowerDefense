package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.PyramideShooteuse;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.CatapulteJAR;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.TotemFlechette;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.PorteDeSable;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def.LanceRocket;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def.Mine;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def.Sniper;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def.SoldatBleu;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.PistoletLaser;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.PistoletLazerMk2;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.Tesla;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.TourDuTemps;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.Archer;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.ElPrimo;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.LanceBuche;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.TourMage;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.ArbreRuste;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.CatapulteCaillou;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.LanceFilet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.MiniVolcan;
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
    @FXML private Label tourDeuxArgent;
    @FXML private Label tourTroisArgent;
    @FXML private Label tourQuatreArgent;
    @FXML private Label labelMessageSys;

    @FXML private ScrollPane paneGlossaire;
    @FXML private VBox contenuGlossaire;

    @FXML private VBox panePause;
    @FXML private VBox paneGameOver;
    @FXML private Label labelDetailGameOver;

    private MediaPlayer musiqueAmbiance;
    @FXML private Slider sliderMusique;
    @FXML private Slider sliderSfx;
    @FXML private Button btnMusique;
    @FXML private Button btnSfx;
    private GestionnaireAudio gestionnaireAudio;
    private boolean muteSfx = false;

    private boolean enPause = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.level = new Level();
        this.vueTerrain = new TerrainVue(backgroundPane);
        this.jeu = new Jeu();
        this.vueEntite = new EntiteVue(entityPane);
        this.vuePreview = new PreviewVue(entityPane, -1,jeu.getPreview(), jeu.getEpoqueActuel());
        this.gestionnaireAudio = new GestionnaireAudio();
        EcouteEntite ecouteEntite = new EcouteEntite(vueEntite, gestionnaireAudio);
        this.uiVue = new UIVue();
        afficherButton(jeu.getEpoqueActuel());

        jeu.getEpoqueActuelProperty().addListener((obs, ancienneEpoque, nouvelleEpoque) -> {
            this.changerNiveau(nouvelleEpoque.intValue());
            lancerMusique(nouvelleEpoque.intValue());
            System.out.println("oui");
        });

        jeu.getEnnemi().addListener(ecouteEntite);
        jeu.getDefenses().addListener(ecouteEntite);
        jeu.getProjectiles().addListener(ecouteEntite);

        labelArgent.textProperty().bind(jeu.getSoldeProperty().asString("Solde : %d"));
        labelVague.textProperty().bind(jeu.getVague().getVagueProperty().add(1).asString("Vague : %d"));
        labelPvBase.textProperty().bind(jeu.getPvBaseProperty().asString("PV : %d"));
        labelCompteurKill.textProperty().bind(jeu.getCompteurKillProperty().asString("Kill : %d"));


        this.afficherPrix(jeu.getEpoqueActuel());

        labelMessageSys.setVisible(false);
        jeu.getVague().getVagueProperty().addListener((obs, old, nouv) -> afficherMessage("Vague " + (nouv.intValue()+1) + " en approche", Color.GOLD, 3));


        ultButton.disableProperty().bind(jeu.getCompteurKillProperty().lessThan(100));

        // Musique
        sliderMusique.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (musiqueAmbiance != null) {
                musiqueAmbiance.setVolume(newVal.doubleValue());
            }
        });
        // Sons
        sliderSfx.valueProperty().addListener((obs, oldVal, newVal) -> {
            gestionnaireAudio.setVolume(newVal.doubleValue());
        });

        initAnimation();
        lancerMusique(jeu.getEpoqueActuel());
        gameLoop.play();

        int[][] donneesMap = level.loadLevel(jeu.getEpoqueActuel());
        vueTerrain.drawMap(donneesMap, jeu.getEpoqueActuel());

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
                    if (!jeu.tick()){
                        gererGameOver();
                    }
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
    public void afficherPrix(int epoque ){
        tourUnArgent.textProperty().bind(uiVue.setPrixT1(epoque,0));
        tourDeuxArgent.textProperty().bind(uiVue.setPrixT1(epoque,1));
        tourTroisArgent.textProperty().bind(uiVue.setPrixT1(epoque,2));
        tourQuatreArgent.textProperty().bind(uiVue.setPrixT1(epoque,3));

    }

    public void recupererPosition(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @FXML
    public void gererTouches(KeyEvent event) {
        switch (event.getCode()) {
            case DIGIT1, NUMPAD1, AMPERSAND -> {
                selectionnerTour(1);
            }
            case DIGIT2, NUMPAD2, UNDEFINED -> {
                selectionnerTour(2);
            }
            case DIGIT3, NUMPAD3, QUOTEDBL-> {
                selectionnerTour(3);
            }
            case DIGIT4, NUMPAD4, QUOTE-> {
                selectionnerTour(4);
            }
            case ESCAPE -> {
                if(this.typeDefenseSelectionnee != 0) {
                    vuePreview.remove();
                    this.typeDefenseSelectionnee = 0;
                    System.out.println("Selection annulée");
                    toggleUI();
                } else if (paneGlossaire.isVisible()) toggleGlossaire();
                else togglePause();
            }
            case L -> {
                if(jeu.getEpoqueActuel() > 0) changerNiveauForcing(jeu.getEpoqueActuel()-1);
            }
            case M -> {
                if (jeu.getEpoqueActuel() < 5)changerNiveauForcing(jeu.getEpoqueActuel()+1);
            }
            case O ->{
                skipVague();
            }
            case I ->{
                motherload();
            }
            case H -> {
                toggleUI();
            }
            case A -> {
                lancerUltime();
            }
            case G -> {
                toggleGlossaire();
            }
            case P -> {
                togglePause();
            }
            case K -> {
                jeu.getCompteurKillProperty().set(jeu.getCompteurKillProperty().get()+10);
            }
            case T -> {
                toggleSpeedBoost();
                if(gameLoop.getRate() > 1) afficherMessage("Vitesse x2.5", Color.GOLD, 1);
                else afficherMessage("Vitesse x1", Color.WHITE, 1);
            }
        }
    }

    public void toggleSpeedBoost(){
        if (gameLoop.getRate() == 1) gameLoop.setRate(2.5);
        else gameLoop.setRate(1);
    }


    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) throws IOException {
        boutonBox.setVisible(true);
        vuePreview.setId(-1);
        vuePreview.remove();

        int xGrille = (int) (mouseEvent.getX() / 64);
        int yGrille = (int) (mouseEvent.getY() / 64);

        if (this.typeDefenseSelectionnee == 0) {
            boolean tourTrouvee = false;

            for (Defense d : jeu.getDefenses()) {
                if (d instanceof Tour) {
                    if ((int) d.getX() == xGrille && (int) d.getY() == yGrille) {
                        d.setSelectionnee(true);
                        tourTrouvee = true;
                        System.out.println("Tu as sélectionné la tour en position x : "+ d.getX() +" y : "+ d.getY());
                        this.afficherMenuTour((Tour) d);
                        vueEntite.afficherCerclePortee((Tour)d);
                    } else {
                        d.setSelectionnee(false);
                    }
                }
            }
            if (!tourTrouvee) {
                this.masquerStatsTour(zoneStats);
                vueEntite.deselectionnerCerclePortee();

                for (Defense d : jeu.getDefenses()) {
                    if (d instanceof Tour) {
                        d.setSelectionnee(false);
                    }
                }
            }
            System.out.println("Veuillez sélectionner une tour d'abord.");
            vuePreview.setId(-1);
            vuePreview.remove();
            return;
        }

        // On appelle la bonne méthode selon l'époque !
        switch (jeu.getEpoqueActuel()) {
            case 0 -> selectionsPrehistoire(xGrille, yGrille);
            case 1 -> selectionAntiquite(xGrille, yGrille);
            case 2 -> selectionsMoyenAge(xGrille, yGrille);
            case 3 -> selectionsContemporain(xGrille, yGrille);
            case 4 -> selectionsFutur(xGrille, yGrille);
            default -> System.out.println("Epoque introuvable");
        }

        this.typeDefenseSelectionnee = 0;
        vuePreview.setId(-1);
        jeu.setId(-1);
        vuePreview.remove();
        this.typeDefenseSelectionnee= 0;
    }

    private void selectionsPrehistoire(int xGrille, int yGrille) {
        switch (typeDefenseSelectionnee) {
            case 1 -> {
                this.piegeSelectione = new MiniVolcan(xGrille, yGrille);
                if (!jeu.poserPiege(piegeSelectione)) {
                    afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
                }
            }
            case 2 -> {
                this.tourSelectionne = new ArbreRuste(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) {
                    afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
                }

            }
            case 3 -> {
                this.tourSelectionne = new CatapulteCaillou(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) {
                    afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
                }
            }
            case 4 -> {
                this.tourSelectionne = new LanceFilet(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) {
                    afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
                }
            }
        }
    }

    private void selectionAntiquite(int xGrille, int yGrille) {
        switch (typeDefenseSelectionnee) {
            case 1 -> {
                this.piegeSelectione = new PorteDeSable(xGrille, yGrille);
                if (!jeu.poserPiege(piegeSelectione)) {
                    afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
                }
            }
            case 2 -> {
                this.tourSelectionne = new TotemFlechette(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) {
                    afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
                }

            }
            case 3 -> {
                this.tourSelectionne = new CatapulteJAR(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) {
                    afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
                }
            }
            case 4 -> {
                this.tourSelectionne = new PyramideShooteuse(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) {
                    afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
                }

            }
        }
    }
    private void selectionsMoyenAge(int xGrille, int yGrille) {
        switch (typeDefenseSelectionnee) {
            case 1 -> {
                this.piegeSelectione = new ElPrimo(xGrille, yGrille);
                if (!jeu.poserPiege(piegeSelectione)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
            case 2 -> {
                this.tourSelectionne = new Archer(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
            case 3 -> {
                this.tourSelectionne = new LanceBuche(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
            case 4 -> {
                this.tourSelectionne = new TourMage(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
        }
    }

    private void selectionsContemporain(int xGrille, int yGrille) {
        switch (typeDefenseSelectionnee) {
            case 1 -> {
                this.piegeSelectione = new Mine(xGrille, yGrille);
                if (!jeu.poserPiege(piegeSelectione)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
            case 2 -> {
                this.tourSelectionne = new SoldatBleu(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
            case 3 -> {
                this.tourSelectionne = new Sniper(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
            case 4 -> {
                this.tourSelectionne = new LanceRocket(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
        }
    }

    private void selectionsFutur(int xGrille, int yGrille) {
        switch (typeDefenseSelectionnee) {
            case 1 -> {
                this.tourSelectionne = new PistoletLaser(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
            case 2 -> {
                this.tourSelectionne = new PistoletLazerMk2(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
            case 3 -> {
                this.tourSelectionne = new Tesla(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
            case 4 -> {
                this.tourSelectionne = new TourDuTemps(xGrille, yGrille);
                if (!jeu.poserTour(tourSelectionne)) afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
            }
        }
    }


    public void masquerStatsTour(Pane zoneStats) {
        zoneStats.getChildren().clear();
        zoneStats.setVisible(false);
    }
    @FXML
    public void poseTour(ActionEvent event){
        Node button = (Button) event.getSource();
        this.typeDefenseSelectionnee = Integer.parseInt(button.getUserData().toString());
        modeSelectionTour();
    }

    public void modeSelectionTour(){
        boutonBox.setVisible(false);
        System.out.println("Défense numéro " + this.typeDefenseSelectionnee +" sélectionnée.");
        vuePreview.setId(this.typeDefenseSelectionnee);
        jeu.setId(this.typeDefenseSelectionnee);
        vuePreview.preview();
    }

    public void selectionnerTour(int tour){
        if (!enPause){
            vuePreview.remove();
            typeDefenseSelectionnee = tour;
            modeSelectionTour();
        }
    }
    // et houi j'ai un passion pour bethoveen étonnant non ? hein bach bach ??? c'est kevin qui a écrit

    public void lancerUltime(){
        jeu.activerUltime();
        if(jeu.getEpoqueActuel() == 0){
            gestionnaireAudio.jouerSonSpecial("meteore");
        } else if (jeu.getEpoqueActuel() == 1){
            gestionnaireAudio.jouerSonSpecial("tornado");
        }
    }

    @FXML
    public void toggleUI(){
        if(boutonBox.isDisabled() || !boutonBox.isVisible()){
            boutonBox.setVisible(true);
        }
        else{
            boutonBox.setVisible(false);
        }
    }
    private void afficherMenuTour(Tour tour) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("properties.fxml"));
            Pane carteState = fxmlLoader.load();

            ControleurStatsTour controller = fxmlLoader.getController();
            controller.updateStats(tour, this.jeu,this);

            zoneStats.getChildren().setAll(carteState);
            zoneStats.setVisible(true);
    }
    public void fermerMenuTour() {
        masquerStatsTour(zoneStats);
        vueEntite.deselectionnerCerclePortee();
    }

    public void afficherMessage(String texte, Color color, int duree){
        uiVue.afficherMessage(texte , color , duree , labelMessageSys );
    }

    public void setDifficulteExtreme(boolean extreme) {
        this.jeu.setModeExtreme(extreme);
        if (extreme) {
            System.out.println("Mode Extrême ! Si vous recommencez, ce sera du tout début hehe");
            afficherMessage("Mode Extrême ! Préparez-vous", Color.DARKRED, 3);
        }
    }

    public void changerNiveau(int nb){
        int[][] donneesMap = level.loadLevel(nb);
        jeu.nuke();
        jeu.newRoute();
        jeu.resetTimersEtVague();

        vueTerrain.drawMap(donneesMap, nb);
        this.vuePreview = new PreviewVue(entityPane, -1, jeu.getPreview(), nb);
        afficherButton(nb);
        afficherPrix(nb);
    }
    public void changerNiveauForcing(int nb){
        int[][] donneesMap = level.loadLevel(nb);
        jeu.setEpoqueActuel(nb);
        jeu.nuke();
        jeu.newRoute();
        jeu.actualiserUltime();
        jeu.resetTimersEtVague();

        vueTerrain.drawMap(donneesMap, nb);
        this.vuePreview = new PreviewVue(entityPane, -1,jeu.getPreview(), nb);
        afficherButton(nb);
        afficherPrix(nb);
    }

    @FXML
    public void toggleGlossaire() {
        if (paneGlossaire.isVisible()) {
            paneGlossaire.setVisible(false);
        } else {
            actualiserGlossaire();
            paneGlossaire.setVisible(true);
        }
    }

    private void actualiserGlossaire() {
        contenuGlossaire.getChildren().clear();
        int epoque = jeu.getEpoqueActuel();

        Label titre = new Label("GLOSSAIRE - ÉPOQUE " + epoque);
        titre.setTextFill(Color.GOLD);
        titre.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
        contenuGlossaire.getChildren().add(titre);

        switch (epoque) {
            case 0:
                ajouterEntreeGlossaire("Arbre Rustique", "Tour basique infligeant des dégâts modérés.", Color.WHITE);
                ajouterEntreeGlossaire("Mini Volcan", "Piège infligeant des dégâts de zone et brûlant les ennemis.", Color.WHITE);
                ajouterEntreeGlossaire("Catapulte", "Lance de lourds cailloux pour des dégâts massifs à l'impact.", Color.WHITE);
                ajouterEntreeGlossaire("Lance-Filet", "Entrave (stun) les ennemis, réduisant considérablement leur vitesse.", Color.WHITE);

                ajouterEntreeGlossaire("Compsognathus", "Petit dinosaure fragile mais qui se déplace en nombre.", Color.LIGHTCORAL);
                ajouterEntreeGlossaire("Vélociraptor", "Rapide et féroce, il fonce vers la base.", Color.LIGHTCORAL);
                ajouterEntreeGlossaire("Tricératops", "Protège la horde en appliquant un bouclier aux alliés proches.", Color.ORANGE);
                ajouterEntreeGlossaire("T-Rex (Boss)", "Étourdit vos défenses avec son rugissement !", Color.RED);
                break;

            case 1:
                ajouterEntreeGlossaire("Mur de Sable", "Piège bloquant les ennemis jusqu'à sa destruction.", Color.WHITE);
                ajouterEntreeGlossaire("Totem à Fléchettes", "Cadence de tir élevée, applique du poison sur la durée.", Color.WHITE);
                ajouterEntreeGlossaire("Catapulte à Jarres", "Fait pleuvoir des jarres explosives causant des effets sur les ennemis.", Color.WHITE);
                ajouterEntreeGlossaire("Pyramide Shooteuse", "Dégâts continus sous forme de rayon qui augmentent sur une même cible.", Color.WHITE);

                ajouterEntreeGlossaire("Momie", "Lente mais résistante aux attaques.", Color.LIGHTCORAL);
                ajouterEntreeGlossaire("Golem de Sable", "Créature massive. Se divise en deux Golimes à sa mort.", Color.ORANGE);
                ajouterEntreeGlossaire("Golime", "Petit fragment issu de la destruction d'un Golem.", Color.LIGHTCORAL);
                ajouterEntreeGlossaire("Boss", "(Ca arrive fort)", Color.RED);
                break;
        }
    }
    private void ajouterEntreeGlossaire(String nom, String description, Color couleurTitre) {
        uiVue.ajouterEntreeGlossaire(nom, description, couleurTitre, contenuGlossaire);
    }

    public void lancerMusique(int epoque){
        if(musiqueAmbiance != null){
            musiqueAmbiance.stop();
        }
        String cheminMusique = "";
        switch (epoque){
            case 1 -> cheminMusique = "media/antiquite.mp3";
            default -> cheminMusique = "media/prehistoire.mp3";
        }

        URL url = Application.class.getResource(cheminMusique);
        if(url != null){
            musiqueAmbiance = new MediaPlayer(new Media(url.toExternalForm()));
            musiqueAmbiance.setVolume(0.2);
            musiqueAmbiance.setCycleCount(MediaPlayer.INDEFINITE);
            musiqueAmbiance.play();
        }
    }

        @FXML
    public void togglePause() {
        if(paneGameOver.isVisible()) return;

        enPause = !enPause;
        if(enPause){
            gameLoop.pause();
            panePause.setVisible(true);
        }
        else{
            gameLoop.play();
            panePause.setVisible(false);
        }
    }

    @FXML
    public void recommencerPartie() {
        paneGameOver.setVisible(false);
        panePause.setVisible(false);
        enPause = false;

        resetPrixTours();
        jeu.getPvBaseProperty().set(50);
        jeu.getSoldeProperty().set(200);

        if (jeu.isModeExtreme()) {
            jeu.getCompteurKillProperty().set(0);
            jeu.actualiserUltime();

            if (jeu.getEpoqueActuel() != 0) {
                jeu.setEpoqueActuel(0);
            } else {
                changerNiveau(0);
            }

        } else {
            changerNiveau(jeu.getEpoqueActuel());
        }

        gameLoop.play();
    }
    private void resetPrixTours() {
        // époque 0
        MiniVolcan.coutPropertyMiniVolcan().set(25);
        ArbreRuste.coutPropertyArbre().set(40);
        CatapulteCaillou.coutPropertyCatapulteCaillou().set(130);
        LanceFilet.coutPropertyLanceFilet().set(125);

        //  époque 1
        PorteDeSable.coutPropertyPorteSable().set(40);
        TotemFlechette.coutPropertyTotemFlechette().set(50);
        CatapulteJAR.coutPropertyCatapulteJar().set(130);
        PyramideShooteuse.coutPropertyPyramideShooteuse().set(140);

    }

    private void gererGameOver() {
        gameLoop.stop();
        vuePreview.remove();
        paneGameOver.setVisible(true);

        if (jeu.isModeExtreme()) {
            labelDetailGameOver.setText("Mode Extrême : Retour à la case départ (Préhistoire) !");
        } else {
            labelDetailGameOver.setText("Mode Normal : Vous pouvez retenter cette époque.");
        }
    }
    @FXML
    public void toggleMuteMusique() {
        if (musiqueAmbiance != null) {
            boolean estMuet = !musiqueAmbiance.isMute();
            musiqueAmbiance.setMute(estMuet);
            btnMusique.setText(estMuet ? "🔇 désactiver" : "🔊 activer");
        }
    }

    @FXML
    public void toggleMuteSfx() {
        muteSfx = !muteSfx;
        gestionnaireAudio.setMute(muteSfx);
        btnSfx.setText(muteSfx ? "🔇 désactiver" : "🔊 activer");
    }
    public void skipVague(){
        jeu.getVague().vagueSuivante();
    }
    public void motherload(){
        jeu.ajouterArgent(200);
    }
}
