package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.bak.timetowerdefense.Application;

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
                ImageView imageView = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/" + tileId + ".png")));

                imageView.setFitWidth(TILE_SIZE);
                imageView.setFitHeight(TILE_SIZE);

                if(tileId == 0){
//                    tile.setFill(Color.GREEN);
                }

//                tile.setX(x * TILE_SIZE);
//                tile.setY(y * TILE_SIZE);
                imageView.setX(x * TILE_SIZE);
                imageView.setY(y * TILE_SIZE);

                backgroundPane.getChildren().add(imageView);
            }
        }
    }
}
