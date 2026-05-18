package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.bak.timetowerdefense.Application;

public class TerrainVue {

    private Pane backgroundPane;

    private static final int TILE_SIZE = 64;


    public TerrainVue(Pane backgroundPane){
        this.backgroundPane = backgroundPane;
    }

    public void drawMap(int[][] map){
        backgroundPane.getChildren().clear();
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                int tileId = map[y][x];


                if (tileId == 7){
                    afficherTile("images/tiles/prehistoire/" + 0 + ".png", x , y );
                }
                else {
                    afficherTile("images/tiles/prehistoire/" + tileId + ".png",x ,y );
                }

                if (tileId == 7){
                    afficherTile("images/tiles/7.png",x,y);

                }
            }
        }
    }
    public void afficherTile(String source, int x, int y){
        ImageView machine =  new ImageView(String.valueOf(Application.class.getResource(source)));
        machine.setFitWidth(TILE_SIZE);
        machine.setFitHeight(TILE_SIZE);
        machine.setX(x * TILE_SIZE);
        machine.setY(y * TILE_SIZE);
        backgroundPane.getChildren().add(machine);
    }
}
