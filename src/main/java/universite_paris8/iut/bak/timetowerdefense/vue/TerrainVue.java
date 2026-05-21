package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.bak.timetowerdefense.Application;

public class TerrainVue {
    private final int  POURCENTAGE_FLEUR = 50;
    private final int  POURCENTAGE_PROPS = 20;
    private Pane backgroundPane;

    private static final int TILE_SIZE = 64;


    public TerrainVue(Pane backgroundPane){
        this.backgroundPane = backgroundPane;
    }

    public void drawMap(int[][] map){

        backgroundPane.getChildren().clear();
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++) {
                afficherSol("prehistoire/", x, y);
            }
        }
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                int tileId = map[y][x];


                if (tileId == 7){
                    afficherTile("",7,x,y);
                }

                else if (tileId == 0 && randomInRange(0,100)< POURCENTAGE_PROPS){
                    {
                        afficherProps("prehistoire/", x, y);

                    }
                }

                else if (tileId != 0){
                    afficherTile("prehistoire/",tileId, x , y);
                }



            }
        }
    }
    public void afficherTile(String epoque,int id, int x, int y){
        ImageView machine =  new ImageView(String.valueOf(Application.class.getResource("images/tiles/"+epoque+ id + ".png" )));
        machine.setFitWidth(TILE_SIZE);
        machine.setFitHeight(TILE_SIZE);
        machine.setX(x * TILE_SIZE);
        machine.setY(y * TILE_SIZE);

        backgroundPane.getChildren().add(machine);
    }
    public void afficherSol(String epoque, int x,int y){
        ImageView machine;
        if (randomInRange(0,100) < POURCENTAGE_FLEUR){
             machine =  new ImageView(String.valueOf(Application.class.getResource("images/tiles/"+epoque+"0"+randomInRange(1,5)+".png" )));
        }
        else {
            machine =  new ImageView(String.valueOf(Application.class.getResource("images/tiles/"+epoque+"000"+randomInRange(1,2)+".png" )));
        }

        machine.setFitWidth(TILE_SIZE);
        machine.setFitHeight(TILE_SIZE);
        machine.setX(x * TILE_SIZE);
        machine.setY(y * TILE_SIZE);

        machine.setRotate(rotationAlea(4));
        backgroundPane.getChildren().add(machine);
    }
    public void afficherProps(String epoque,int x, int y){
        ImageView machine =  new ImageView(String.valueOf(Application.class.getResource("images/tiles/"+epoque+"00"+randomInRange(1,5)+".png" )));
        machine.setFitWidth(TILE_SIZE);
        machine.setFitHeight(TILE_SIZE);
        machine.setX(x * TILE_SIZE);
        machine.setY(y * TILE_SIZE);

        backgroundPane.getChildren().add(machine);
    }

    public int rotationAlea(int n){
        int randomNum = (int)(Math.random() * (n+1));
        return 90*randomNum;
    }
    public int randomInRange(int min, int max){
        return (int)(Math.random() * (max - min) + min);
    }
}
