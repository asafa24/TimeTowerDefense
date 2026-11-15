package universite_paris8.iut.bak.timetowerdefense;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 832, 704);
        stage.setTitle("Time Tower Defense");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("images/tiles/logo.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}