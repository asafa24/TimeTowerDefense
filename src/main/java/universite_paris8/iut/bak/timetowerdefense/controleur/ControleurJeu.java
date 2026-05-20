package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.bak.timetowerdefense.modele.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.Jeu;
import universite_paris8.iut.bak.timetowerdefense.modele.Level;
import universite_paris8.iut.bak.timetowerdefense.modele.Vague;
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
    private int temps,delay;


    private Level level;
    private Vague vague;
    private TerrainVue vueTerrain;
    private Jeu jeu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.level = new Level();
        this.vueTerrain = new TerrainVue(backgroundPane);
        this.vague = new Vague();
        this.jeu = new Jeu();
        vague.test();
        initAnimation();
        gameLoop.play();

        int[][] donneesMap = level.loadLevel(0);
        vueTerrain.drawMap(donneesMap);
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        delay = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        List<Point2D> route = level.calculerChemin(0, new Point2D(0, 9), new Point2D(10, 1));
        System.out.println("Chemin trouvé : " + route);
        vague.test();





        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev -> {
                    if (temps%80 == 0 && !vague.getQueue().isEmpty()) {
                        jeu.addEnnemi(creerEnnemi(route, couleur(vague.defiler())));
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



}