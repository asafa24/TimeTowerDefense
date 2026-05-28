package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.bak.timetowerdefense.Application;

public class UIVue {

    public UIVue() {
    }

    public ImageView setImageT1(int epoque, int button){
        String epoque_txt;
        switch (epoque){
            case 0:
                epoque_txt = "prehistoire/";
                break;
            default:
                epoque_txt = "prehistoire/";
                break;
        }

        Image img = new Image(String.valueOf(Application.class.getResource("images/tiles/" + epoque_txt +"button/b_0" + button +".png" )));
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(64);
        imageView.setFitWidth(64);
        imageView.setPreserveRatio(true);
        return imageView;

    }
}
