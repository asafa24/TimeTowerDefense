package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.bak.timetowerdefense.modele.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.Level;
import universite_paris8.iut.bak.timetowerdefense.vue.TerrainVue;

import java.net.URL;
import javafx.util.Duration;
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
    @FXML
    public void handleMouseClick(MouseEvent event) {

        int x = (int) Math.floor(event.getX() / 64);
        int y = (int) Math.floor(event.getY() / 64);


        System.out.println(x + ":" + y);
        System.out.println("Tour : " + peuxPoserTour(0,x,y));
        System.out.println("Piege : " + peuxPoserPiege(0, x, y));
    }
    public boolean peuxPoserTour (int epoque, int x ,int y){
        int [][] test = level.loadLevel(epoque);
        return test[y][x] == 0;
    }
    public boolean peuxPoserPiege (int epoque, int x ,int y){
        int [][] test = level.loadLevel(epoque);
        return test[y][x] > 0 && test[y][x] <= 6;
    }


    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        Ennemi e = new Ennemi(64, 64, 50, 2, 10);

        Rectangle r = new Rectangle(32, 32, Color.DARKRED);
        r.translateXProperty().bind(e.xProperty());
        r.translateYProperty().bind(e.yProperty());

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