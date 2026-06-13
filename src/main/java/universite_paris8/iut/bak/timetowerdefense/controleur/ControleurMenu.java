package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import universite_paris8.iut.bak.timetowerdefense.Application;

import java.io.IOException;
import java.net.URL;

public class ControleurMenu {

    @FXML private RadioButton radioExtreme;
    @FXML private StackPane rootPane;

    @FXML
    public void lancerJeu(ActionEvent event) {
        boolean isModeExtreme = radioExtreme.isSelected();

        URL url = Application.class.getResource("media/transition.mp4");
        if (url == null) {
            System.err.println("Vidéo introuvable, lancement direct.");
            chargerFenetreJeu(event, isModeExtreme);
            return;
        }

        Media media = new Media(url.toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.fitWidthProperty().bind(rootPane.widthProperty());
        mediaView.fitHeightProperty().bind(rootPane.heightProperty());
        mediaView.setPreserveRatio(true);

        rootPane.getChildren().add(mediaView);

        mediaPlayer.setOnEndOfMedia(() -> {
            chargerFenetreJeu(event, isModeExtreme);
        });

        mediaPlayer.play();
    }

    private void chargerFenetreJeu(ActionEvent event, boolean isModeExtreme) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("main.fxml"));
            Parent root = loader.load();

            ControleurJeu controleurJeu = loader.getController();
            controleurJeu.setDifficulteExtreme(isModeExtreme);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 832, 704);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}