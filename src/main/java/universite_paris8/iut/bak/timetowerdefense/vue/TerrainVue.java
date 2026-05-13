package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TerrainVue {

    private Pane backgroundPane;

    private static final int TILE_SIZE = 32;


    public TerrainVue(Pane backgroundPane){
        this.backgroundPane = backgroundPane;
    }

    public void drawMap(int[][] map){
        backgroundPane.getChildren().clear();
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                int tileId = map[y][x];

                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);

                if(tileId == 0){
                    tile.setFill(Color.GREEN);
                }

                tile.setX(x * TILE_SIZE);
                tile.setY(y * TILE_SIZE);

                backgroundPane.getChildren().add(tile);
            }
        }
    }
}
