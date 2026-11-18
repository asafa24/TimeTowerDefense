package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Epoque;

public class TerrainVue {
    private final int POURCENTAGE_FLEUR = 50;
    private final int POURCENTAGE_PROPS = 20;
    private Pane backgroundPane;

    private static final int TILE_SIZE = 64;
    private Epoque epoque;
    private String nomRepertoire;

    public TerrainVue(Pane backgroundPane, Epoque epoque) {
        this.backgroundPane = backgroundPane;
        this.epoque = epoque;
        this.nomRepertoire = this.epoque.toString().toLowerCase() + "/";
    }

    public void drawMap(/* int[][] map, int epoque */) {

        // switch (epoque) {
        // case 1 -> {
        // this.epoque = "antiquite/";
        // }
        // case 2 -> {
        // this.epoque = "moyen-age/";
        // }
        // case 3 -> {
        // this.epoque = "contemporain/";
        // }
        // case 4 -> {
        // this.epoque = "future/";
        // }
        // default -> {
        // this.epoque = "prehistoire/";
        // }
        // }

        backgroundPane.getChildren().clear();
        for (int y = 0; y < this.epoque.hauteurMap(); y++) {
            for (int x = 0; x < this.epoque.longueurMap(); x++) {
                afficherSol(this.nomRepertoire, x, y);
            }
        }
        for (int y = 0; y < this.epoque.hauteurMap(); y++) {
            for (int x = 0; x < this.epoque.longueurMap(); x++) {
                // int tileId = map[y][x];
                int tileId = this.epoque.valeurCoordonnee(x, y);

                if (tileId == 7) {
                    afficherTile("", 7, x, y);
                }

                else if (tileId == 0 && randomInRange(0, 100) < POURCENTAGE_PROPS) {
                    {
                        afficherProps(this.nomRepertoire, x, y);

                    }
                }

                else if (tileId != 0) {
                    afficherTile(this.nomRepertoire, tileId, x, y);
                }

            }
        }
    }

    public void afficherTile(String repertoire, int id, int x, int y) {
        ImageView machine = new ImageView(
                String.valueOf(Application.class.getResource("images/tiles/" + repertoire + id + ".png")));
        machine.setFitWidth(TILE_SIZE);
        machine.setFitHeight(TILE_SIZE);
        machine.setX(x * TILE_SIZE);
        machine.setY(y * TILE_SIZE);

        backgroundPane.getChildren().add(machine);
    }

    public void afficherSol(String repertoire, int x, int y) {
        ImageView machine;
        try {
            if (randomInRange(0, 100) < POURCENTAGE_FLEUR) {
                machine = new ImageView(String.valueOf(
                        Application.class
                                .getResource("images/tiles/" + repertoire + "0" + randomInRange(1, 5) + ".png")));
            } else {
                machine = new ImageView(String.valueOf(Application.class
                        .getResource("images/tiles/" + repertoire + "000" + randomInRange(1, 3) + ".png")));
            }
        } catch (Exception e) {
            machine = new ImageView(String.valueOf(Application.class.getResource("images/tiles/noTexture.png")));
            System.out.println("0 ou 000");
        }

        machine.setFitWidth(TILE_SIZE);
        machine.setFitHeight(TILE_SIZE);
        machine.setX(x * TILE_SIZE);
        machine.setY(y * TILE_SIZE);

        machine.setRotate(rotationAlea(4));
        backgroundPane.getChildren().add(machine);
    }

    public void afficherProps(String repertoire, int x, int y) {
        ImageView machine;
        try {
            machine = new ImageView(String.valueOf(
                    Application.class.getResource("images/tiles/" + repertoire + "00" + randomInRange(1, 5) + ".png")));
        } catch (Exception e) {
            machine = new ImageView(String.valueOf(Application.class.getResource("images/tiles/noTexture.png")));
            System.out.println(00);
        }

        machine.setFitWidth(TILE_SIZE);
        machine.setFitHeight(TILE_SIZE);
        machine.setX(x * TILE_SIZE);
        machine.setY(y * TILE_SIZE);

        backgroundPane.getChildren().add(machine);
    }

    public int rotationAlea(int n) {
        int randomNum = (int) (Math.random() * (n + 1));
        return 90 * randomNum;
    }

    public int randomInRange(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}
