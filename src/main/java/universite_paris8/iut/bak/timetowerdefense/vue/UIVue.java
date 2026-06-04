package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.controleur.PropertiController;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourStun;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.CatapulteOs;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.LanceFilet;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

import java.io.IOException;

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
    public void setImageEtNom(ImageView p_image, Tour tour, Label p_nom){
        if (tour instanceof CatapulteOs) {
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