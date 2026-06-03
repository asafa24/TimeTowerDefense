package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.beans.binding.Bindings;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.preview.Preview;

public class PreviewVue {
    private Pane entityPane;
    private static int TILE_SIZE = 64;
    private int id;
    private Preview preview ;
    private ImageView img ;

    public PreviewVue(Pane entityPane, int id, Preview preview) {
        this.entityPane = entityPane;
        this.id = id;
        this.preview = preview;
        preview();
        System.out.println("est créé");

    }

    public void setId(int id) {
        this.id = id;
    }

    public void preview(){
        if (id != -1) {
            if (id == 1) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/arbre.png")));
                System.out.println("oui le 1");

            }
            else if (id == 2) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/catapulteT2.png")));
                System.out.println("oui le 2");
            }
            else if (id == 3) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/LanceFilet.png")));
                System.out.println("oui le 3");

            }
            else if (id == 4) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/volcan.png")));
                System.out.println("oui le 4");
            }
            else {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/arbre.png")));
                System.out.println("id = " + id);
            }

            img.translateXProperty().bind(
                    Bindings.createDoubleBinding(
                            () -> (double) (Math.floor(preview.xProperty().getValue() / TILE_SIZE) * TILE_SIZE),
                            preview.xProperty()
                    )
            );
            img.translateYProperty().bind(
                    Bindings.createDoubleBinding(
                            () -> (double) (Math.floor(preview.yProperty().getValue() / TILE_SIZE) * TILE_SIZE),
                            preview.yProperty()
                    )
            );
            img.setFitHeight(TILE_SIZE);
            img.setFitWidth(TILE_SIZE);

            System.out.println(preview.xProperty());
            System.out.println(preview.yProperty());
            entityPane.getChildren().add(img);
        }
    }

    public void remove(){
        entityPane.getChildren().remove(img);
    }
}
