package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.bak.timetowerdefense.Application;

public class UIVue {

    public UIVue() {
    }

    public ImageView setImageT1(){
        Image img = new Image(String.valueOf(Application.class.getResource("images/tiles/prehistoire/button/b_01.png")));
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(64);
        imageView.setFitWidth(64);
        imageView.setPreserveRatio(true);
        return imageView;

    }
}
