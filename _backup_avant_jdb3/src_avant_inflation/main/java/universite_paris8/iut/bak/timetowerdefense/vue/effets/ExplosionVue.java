package universite_paris8.iut.bak.timetowerdefense.vue.effets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.bak.timetowerdefense.Application;

public class ExplosionVue {
    int x,y,time,sprite;
    ImageView img0;
    private Pane effectPane;

    public ExplosionVue(int x, int y, Pane effectPane) {
        this.x = x;
        this.y = y;
        time = 0;
        sprite = 0;
        this.effectPane = effectPane;




         img0 = new ImageView(String.valueOf(Application.class.getResource("images/tiles/effets/explosion_0.png")));
         effectPane.getChildren().add(img0);


    }
    public void tick(){

        if (time%12 == 0){
            effectPane.getChildren().remove(img0);
        }
        time++;


    }

}
