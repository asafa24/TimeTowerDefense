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
    private boolean transitionEnCours;
    private MediaPlayer lecteurTransition;
    private PauseTransition surveillanceTransition;

    @FXML
    public void lancerJeu(ActionEvent event) {
        if (lancementEnCours || transitionEnCours) {
            return;
        }

        boolean isModeExtreme = radioExtreme.isSelected();
        URL url = Application.class.getResource("media/transition.mp4");
        if (url == null) {
            System.err.println("Vidéo introuvable, lancement direct.");
            ouvrirJeu(event, isModeExtreme);
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
            media.setOnError(() -> {
                System.err.println("Erreur de chargement de la transition vidéo, lancement direct.");
                ouvrirJeu(event, isModeExtreme);
            });

            // Le délai est toujours actif : le lecteur peut rester bloqué en état PLAYING.
            transitionEnCours = true;
            surveillanceTransition = new PauseTransition(Duration.seconds(6));
            surveillanceTransition.setOnFinished(e -> {
                if (!lancementEnCours) {
                    System.err.println("La transition vidéo a dépassé le délai, lancement direct.");
                    ouvrirJeu(event, isModeExtreme);
                }
            });
            surveillanceTransition.play();

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
        transitionEnCours = false;
        if (surveillanceTransition != null) {
            surveillanceTransition.stop();
        }
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
