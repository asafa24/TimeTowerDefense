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
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.*;

import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Level;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Triche;
import universite_paris8.iut.bak.timetowerdefense.vue.EntiteVue;
import universite_paris8.iut.bak.timetowerdefense.vue.PreviewVue;
import universite_paris8.iut.bak.timetowerdefense.vue.TerrainVue;

import universite_paris8.iut.bak.timetowerdefense.modele.fabriques.*;

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import universite_paris8.iut.bak.timetowerdefense.vue.UIVue;
import java.util.List;
import java.util.ResourceBundle;

public class ControleurJeu implements Initializable {
    @FXML private StackPane paneMain;
    @FXML private Button btnVitesse;
    @FXML private Pane backgroundPane;
    @FXML private Pane entityPane;
    @FXML private Label labelPvBase;
    @FXML private Label labelArgent;
    @FXML private Label labelVague;
    @FXML private Button tourUn;
    @FXML private Button tourDeux;
    @FXML private Button tourTrois;
    @FXML private Button tourQuatre;
    @FXML private HBox boutonBox;
    @FXML private Button ultButton;
    @FXML private Label labelCompteurKill;
    private List<Button> boutonsTour;
    private List<Label> labelsPrix;
    private final List<FabriqueDefense> fabriques = List.of(
            new FabriquePrehistoire(), new FabriqueAntiquite(), new FabriqueMoyenAge(),
            new FabriqueContemporain(), new FabriqueFutur());
    private Timeline gameLoop;
    private double mouseX, mouseY;
    private Level level;
    private TerrainVue vueTerrain;
    private EntiteVue vueEntite;
    private PreviewVue vuePreview;
    private UIVue uiVue;
    private Jeu jeu;
    private Triche triche;
    private int typeDefenseSelectionnee = 0;
    @FXML private Pane zoneStats;
    @FXML private Label tourUnArgent;
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

        this.boutonsTour = List.of(tourUn, tourDeux, tourTrois, tourQuatre);
        this.labelsPrix = List.of(tourUnArgent, tourDeuxArgent, tourTroisArgent, tourQuatreArgent);

        this.jeu = new Jeu();
        this.triche = new Triche(jeu);
        this.vueEntite = new EntiteVue(entityPane);
        this.vuePreview = new PreviewVue(entityPane, -1, jeu.getPreview(), jeu.getEpoqueActuel());
        this.gestionnaireAudio = new GestionnaireAudio();
        EcouteEntite ecouteEntite = new EcouteEntite(vueEntite, gestionnaireAudio);
        this.uiVue = new UIVue();
        afficherButton(jeu.getEpoqueActuel());

        jeu.getEpoqueActuelProperty().addListener((obs, ancienneEpoque, nouvelleEpoque) -> {
            this.changerNiveau(nouvelleEpoque.intValue());
            lancerMusique(nouvelleEpoque.intValue());
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
        jeu.getVague().getVagueProperty().addListener(
                (obs, old, nouv) -> afficherMessage("Vague " + (nouv.intValue() + 1) + " en approche", Color.GOLD, 3));

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
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev -> {
                    if (!jeu.tick()) {
                        gererGameOver();
                    }
                    if (typeDefenseSelectionnee != 0) {
                        jeu.preview(mouseX, mouseY);
                    }
                }));
        gameLoop.getKeyFrames().add(kf);
    }

    public void afficherButton(int epoque) {
        for (int i = 0; i < boutonsTour.size(); i++) {
            boutonsTour.get(i).setGraphic(uiVue.setImageT1(epoque, i));
        }
    }

    public void afficherPrix(int epoque) {
        for (int i = 0; i < labelsPrix.size(); i++) {
            labelsPrix.get(i).textProperty().bind(uiVue.setPrixT1(epoque, i));
        }
    }

    public void recupererPosition(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @FXML
    public void gererTouches(KeyEvent event) {
        if (gererTouchesTriche(event)) {
            return;
        }
        switch (event.getCode()) {
            case DIGIT1, NUMPAD1, AMPERSAND -> selectionnerTour(1);
            case DIGIT2, NUMPAD2, UNDEFINED -> selectionnerTour(2);
            case DIGIT3, NUMPAD3, QUOTEDBL -> selectionnerTour(3);
            case DIGIT4, NUMPAD4, QUOTE -> selectionnerTour(4);
            case ESCAPE -> {
                if (this.typeDefenseSelectionnee != 0) {
                    vuePreview.remove();
                    this.typeDefenseSelectionnee = 0;
                    toggleUI();
                } else if (paneGlossaire.isVisible())
                    toggleGlossaire();
                else
                    togglePause();
            }
            case H -> toggleUI();
            case A -> lancerUltime();
            case G -> toggleGlossaire();
            case P -> togglePause();
        }
    }

    @FXML
    private boolean gererTouchesTriche(KeyEvent event) {
        switch (event.getCode()) {
            case L -> {
                if (jeu.getEpoqueActuel() > 0)
                    changerNiveauForcing(jeu.getEpoqueActuel() - 1);
                return true;
            }
            case M -> {
                if (jeu.getEpoqueActuel() < 5)
                    changerNiveauForcing(jeu.getEpoqueActuel() + 1);
                return true;
            }
            case O -> { triche.tricheVague(); return true; }
            case I -> { triche.tricheArgent(200); return true; }
            case K -> { triche.tricheKills(10); return true; }
            case T -> {
                boolean estAccelere = triche.toggleSpeedBoost(gameLoop);
                if (estAccelere) {
                    afficherMessage("Vitesse x2.5", Color.GOLD, 1);
                } else {
                    afficherMessage("Vitesse x1", Color.WHITE, 1);
                }
                return true;
            }
            default -> { return false; }
        }
    }

    @FXML
    public void changerVitesseJeu(ActionEvent event) {
        boolean estAccelere = triche.toggleSpeedBoost(gameLoop);
        if (estAccelere) {
            btnVitesse.setText("VITESSE : x2.5");
            afficherMessage("Vitesse x2.5", Color.GOLD, 1);
        } else {
            btnVitesse.setText("VITESSE : x1");
            afficherMessage("Vitesse x1", Color.WHITE, 1);
        }
    }

    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) throws IOException {
        boutonBox.setVisible(true);
        vuePreview.setId(-1);
        vuePreview.remove();

        int xGrille = (int) (mouseEvent.getX() / 64);
        int yGrille = (int) (mouseEvent.getY() / 64);

        if (this.typeDefenseSelectionnee == 0) {
            selectionnerTourSurCase(xGrille, yGrille);
        } else {
            poserDefenseSurCase(xGrille, yGrille);
        }
    }

    private void selectionnerTourSurCase(int xGrille, int yGrille) throws IOException {
        boolean tourTrouvee = false;

        for (Defense d : jeu.getDefenses()) {
            if (d instanceof Tour) {
                if ((int) d.getX() == xGrille && (int) d.getY() == yGrille) {
                    d.setSelectionnee(true);
                    tourTrouvee = true;
                    this.afficherMenuTour((Tour) d);
                    vueEntite.afficherCerclePortee((Tour) d);
                } else {
                    d.setSelectionnee(false);
                }
            }
        }
        if (!tourTrouvee) {
            this.masquerStatsTour(zoneStats);
            vueEntite.deselectionnerCerclePortee();
        }
    }

    private void poserDefenseSurCase(int xGrille, int yGrille) {
        FabriqueDefense fabrique = fabriques.get(jeu.getEpoqueActuel());
        tenterPoserDefense(fabrique.creerDefense(typeDefenseSelectionnee, xGrille, yGrille));

        this.typeDefenseSelectionnee = 0;
        jeu.setId(-1);
    }

    private void tenterPoserDefense(Defense defense) {
        boolean succes = false;
        if (defense instanceof Tour) {
            succes = jeu.poserTour((Tour) defense);
        } else if (defense instanceof Piege) {
            succes = jeu.poserPiege((Piege) defense);
        }
        if (!succes) {
            afficherMessage("Solde insuffisant ou Case invalide", Color.RED, 2);
        } else {
            afficherPrix(jeu.getEpoqueActuel());
        }
    }
    public void masquerStatsTour(Pane zoneStats) {
        zoneStats.getChildren().clear();
        zoneStats.setVisible(false);
    }

    @FXML
    public void poseTour(ActionEvent event) {
        Node button = (Button) event.getSource();
        this.typeDefenseSelectionnee = Integer.parseInt(button.getUserData().toString());
        modeSelectionTour();
    }

    public void modeSelectionTour() {
        boutonBox.setVisible(false);
        vuePreview.setId(this.typeDefenseSelectionnee);
        jeu.setId(this.typeDefenseSelectionnee);
        vuePreview.preview();
    }

    public void selectionnerTour(int tour) {
        if (!enPause) {
            vuePreview.remove();
            typeDefenseSelectionnee = tour;
            modeSelectionTour();
        }
    }

    public void lancerUltime() {
        jeu.activerUltime();
        if (jeu.getEpoqueActuel() == 0) {
            gestionnaireAudio.jouerSonSpecial("meteore");
        } else if (jeu.getEpoqueActuel() == 1) {
            gestionnaireAudio.jouerSonSpecial("tornado");
        }
    }

    @FXML
    public void toggleUI() {
        if (boutonBox.isDisabled() || !boutonBox.isVisible()) {
            boutonBox.setVisible(true);
        } else {
            boutonBox.setVisible(false);
        }
    }

    private void afficherMenuTour(Tour tour) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("properties.fxml"));
        Pane carteState = fxmlLoader.load();

        ControleurStatsTour controller = fxmlLoader.getController();
        controller.updateStats(tour, this.jeu, this);

        zoneStats.getChildren().setAll(carteState);
        zoneStats.setVisible(true);
    }

    public void fermerMenuTour() {
        masquerStatsTour(zoneStats);
        vueEntite.deselectionnerCerclePortee();
    }

    public void afficherMessage(String texte, Color color, int duree) {
        uiVue.afficherMessage(texte, color, duree, labelMessageSys);
    }

    public void setDifficulteExtreme(boolean extreme) {
        this.jeu.setModeExtreme(extreme);
        if (extreme) {
            System.out.println("Mode Extrême ! Si vous recommencez, ce sera du tout début hehe");
            afficherMessage("Mode Extrême ! Préparez-vous", Color.DARKRED, 3);
        }
    }

    private void chargerVisuelsNiveau(int nb) {
        int[][] donneesMap = level.loadLevel(nb);
        vueTerrain.drawMap(donneesMap, nb);
        this.vuePreview = new PreviewVue(entityPane, -1, jeu.getPreview(), nb);
        afficherButton(nb);
        afficherPrix(nb);
    }

    public void changerNiveau(int nb) {
        jeu.nuke();
        jeu.newRoute();
        jeu.resetTimersEtVague();
        chargerVisuelsNiveau(nb);
    }

    public void changerNiveauForcing(int nb) {
        triche.tricheChangeEpoque(nb);
        chargerVisuelsNiveau(nb);
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

    public void lancerMusique(int epoque) {
        if (musiqueAmbiance != null) {
            musiqueAmbiance.stop();
        }
        String cheminMusique = "";
        switch (epoque) {
            case 1 -> cheminMusique = "media/antiquite.mp3";
            default -> cheminMusique = "media/prehistoire.mp3";
        }

        URL url = Application.class.getResource(cheminMusique);
        if (url != null) {
            try {
                musiqueAmbiance = new MediaPlayer(new Media(url.toExternalForm()));
                musiqueAmbiance.setVolume(0.2);
                musiqueAmbiance.setCycleCount(MediaPlayer.INDEFINITE);
                musiqueAmbiance.play();
            } catch (MediaException e) {
                System.err.println("Musique indisponible : " + e.getMessage());
                musiqueAmbiance = null;
            }
        }
    }

    @FXML
    public void togglePause() {
        if (paneGameOver.isVisible())
            return;

        enPause = !enPause;
        if (enPause) {
            gameLoop.pause();
            panePause.setVisible(true);
        } else {
            gameLoop.play();
            panePause.setVisible(false);
        }
    }

    @FXML
    public void recommencerPartie() {
        paneGameOver.setVisible(false);
        panePause.setVisible(false);
        enPause = false;

        Defense.reinitialiserPrix();
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
}