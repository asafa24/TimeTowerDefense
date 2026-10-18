package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.animation.PauseTransition;
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
import javafx.util.Duration;
import universite_paris8.iut.bak.timetowerdefense.Application;

import java.io.IOException;
import java.net.URL;

public class ControleurMenu {

    @FXML private RadioButton radioExtreme;
    @FXML private StackPane rootPane;

    private boolean lancementEnCours;
    private MediaPlayer lecteurTransition;

    @FXML
    public void lancerJeu(ActionEvent event) {
        if (lancementEnCours) {
            return;
        }

        boolean isModeExtreme = radioExtreme.isSelected();
        URL url = Application.class.getResource("media/transition.mp4");
        if (url == null) {
            System.err.println("Vidéo introuvable, lancement direct.");
            chargerFenetreJeu(event, isModeExtreme);
            return;
        }

        try {
            Media media = new Media(url.toExternalForm());
            lecteurTransition = new MediaPlayer(media);
            MediaView mediaView = new MediaView(lecteurTransition);

            mediaView.fitWidthProperty().bind(rootPane.widthProperty());
            mediaView.fitHeightProperty().bind(rootPane.heightProperty());
            mediaView.setPreserveRatio(true);
            rootPane.getChildren().add(mediaView);

            lecteurTransition.setOnEndOfMedia(() -> ouvrirJeu(event, isModeExtreme));
            lecteurTransition.setOnError(() -> {
                System.err.println("Erreur pendant la transition vidéo, lancement direct.");
                ouvrirJeu(event, isModeExtreme);
            });

            // Certaines erreurs de lecture ne déclenchent pas toujours le callback d'erreur.
            PauseTransition surveillance = new PauseTransition(Duration.seconds(10));
            surveillance.setOnFinished(e -> {
                if (!lancementEnCours && lecteurTransition.getStatus() != MediaPlayer.Status.PLAYING) {
                    System.err.println("La transition vidéo ne démarre pas, lancement direct.");
                    ouvrirJeu(event, isModeExtreme);
                }
            });
            surveillance.play();

            lecteurTransition.play();
        } catch (RuntimeException e) {
            System.err.println("Impossible de lancer la transition vidéo, lancement direct.");
            e.printStackTrace();
            ouvrirJeu(event, isModeExtreme);
        }
    }

    private void ouvrirJeu(ActionEvent event, boolean isModeExtreme) {
        if (lancementEnCours) {
            return;
        }
        lancementEnCours = true;
        if (lecteurTransition != null) {
            lecteurTransition.stop();
            lecteurTransition.dispose();
        }
        chargerFenetreJeu(event, isModeExtreme);
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
