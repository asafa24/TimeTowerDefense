package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.beans.binding.Bindings;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Entite;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourStun;
import universite_paris8.iut.bak.timetowerdefense.modele.preview.Preview;

public class PreviewVue {
    private Pane entityPane;
    private static int TILE_SIZE = 64;
    private int id;
    private Preview preview ;
    private ImageView img = new ImageView();

    public PreviewVue(Pane entityPane, int id, Preview preview) {
        this.entityPane = entityPane;
        this.id = id;
        this.preview = preview;
        preview();

    }

    public void setId(int id) {
        this.id = id;
    }

    public void preview(){
        if (id != -1) {
            ImageView img = new ImageView();
            if (id == 0) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/arbre.png")));


            }
            if (id == 1) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/catapulteT2.png")));

            }
            if (id == 2) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/LanceFilet.png")));

            }
            if (id == 3) {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/arbre.png")));
            }
            else {
                img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/arbre.png")));
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
