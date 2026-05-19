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
import universite_paris8.iut.bak.timetowerdefense.modele.Level;
import universite_paris8.iut.bak.timetowerdefense.vue.TerrainVue;

import java.net.URL;
import javafx.util.Duration;

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
    private TerrainVue vueTerrain;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.level = new Level();
        this.vueTerrain = new TerrainVue(backgroundPane);

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

        Ennemi e = new Ennemi(0, 64 * 9, 50, 2, 10, route);

        Rectangle r = new Rectangle(32, 32, Color.DARKRED);
        r.translateXProperty().bind(e.xProperty().add(16));
        r.translateYProperty().bind(e.yProperty().add(16));

        r.setOpacity(0.80);
        entityPane.getChildren().add(r);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev -> {
                    e.avancer();
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
}