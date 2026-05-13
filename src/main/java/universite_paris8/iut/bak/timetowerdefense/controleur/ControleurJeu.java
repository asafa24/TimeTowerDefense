package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import universite_paris8.iut.bak.timetowerdefense.modele.Level;
import universite_paris8.iut.bak.timetowerdefense.vue.TerrainVue;

import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;

public class ControleurJeu implements Initializable {

    @FXML
    private Pane backgroundPane;

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

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev -> {
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
}