package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UIVue {
    private Button t1;
    public UIVue(Button t1) {
        this.t1 = t1;
    }

    public void setImageT1(){
        Image img = new Image("file:resources/images/prehistoire/button/b_01");
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(64);
        imageView.setFitWidth(64);
        t1.setGraphic(imageView);
    }
}
