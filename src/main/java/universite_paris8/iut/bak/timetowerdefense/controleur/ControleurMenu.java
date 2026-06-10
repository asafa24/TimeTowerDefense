package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import universite_paris8.iut.bak.timetowerdefense.Application;

import java.io.IOException;

public class ControleurMenu {

    @FXML
    private RadioButton radioExtreme;

    @FXML
    public void lancerJeu(ActionEvent event) {
        boolean isModeExtreme = radioExtreme.isSelected();
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
            System.err.println("Erreur lors du chargement de main.fxml");
        }
    }
}