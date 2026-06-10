package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.CatapulteCaillou;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.LanceFilet;

public class UIVue {

    public UIVue() {
    }

    public ImageView setImageT1(int epoque, int button){
        String epoque_txt;
        switch (epoque){
            case 1:
                epoque_txt = "antiquite/";
                break;
            default:
                epoque_txt = "prehistoire/";
                break;
        }
        Image img ;
        try {
             img = new Image(String.valueOf(Application.class.getResource("images/tiles/" + epoque_txt + "button/b_0" + button + ".png")));
        }
        catch (Exception e){
             img = new Image(String.valueOf(Application.class.getResource("images/tiles/noTexture.png")));
        }
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(64);
        imageView.setFitWidth(64);
        imageView.setPreserveRatio(true);
        return imageView;
    }
    public void setImageEtNom(ImageView p_image, Tour tour, Label p_nom){
        if (tour instanceof CatapulteCaillou) {
            p_nom.setText("Lance-pierre");
            p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/catapulteT2.png"))));
        } else if (tour instanceof LanceFilet) {
            p_nom.setText("Filetoorr");
            p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/LanceFilet.png"))));
        } else {
            p_nom.setText("Arbre rustre");
            p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/arbre.png"))));
        }
    }
}