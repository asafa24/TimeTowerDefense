package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.controleur.PropertiController;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
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

    public void afficherStatsTour(Tour tour, Pane zoneStats, Jeu jeu) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("properties.fxml"));
        Pane carteState = fxmlLoader.load();

        PropertiController controller = fxmlLoader.getController();
        controller.updateStats(tour, jeu);

        zoneStats.getChildren().setAll(carteState);
        zoneStats.setVisible(true);
        System.out.printf("affihcerrrr");
    }

    public void masquerStatsTour(Pane zoneStats) {
        zoneStats.getChildren().clear();
        zoneStats.setVisible(false);
    }
}