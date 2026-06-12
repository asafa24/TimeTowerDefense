package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.beans.binding.Bindings;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.preview.Preview;

public class PreviewVue {
    private Pane entityPane;
    private static int TILE_SIZE = 64;
    private int id;
    private Preview preview ;
    private ImageView img ;
    private Blend blush;
    private ColorAdjust monochrome ;
    private int epoque;

    public PreviewVue(Pane entityPane, int id, Preview preview,int epoque) {
        this.entityPane = entityPane;
        this.id = id;
        this.preview = preview;
        this.epoque = epoque;



        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1.0);

        preview();



    }

    public void setId(int id) {
        this.id = id;
    }

    public void preview(){
        if (id != -1) {
            switch (epoque){
                case 0 ->{
                    if (id == 1) {
                        img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/volcan.png")));



                    }
                    else if (id == 2) {
                        img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/arbre.png")));


                    }
                    else if (id == 3) {
                        img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/catapulteT2.png")));



                    }
                    else if (id == 4) {
                        img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/LanceFilet.png")));

                    }
                    else {
                        img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/arbre.png")));

                    }
                }
                case 1 ->{
                    switch (id){
                        case 1 -> {
                            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/tour/murDeSable.png")));
                        }
                        case 2 -> {
                            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/tour/piegeFlechette.png")));
                        }
                        case 3 -> {
                            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/tour/catapulteJar.png")));
                        }
                        case 4 -> {
                            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/tour/piramidJ.png")));
                        }
                        default -> {
                            img = new ImageView(String.valueOf(Application.class.getResource("images/tiles/noTexture.png")));
                        }
                    }
                }
            }

            Light.Distant redLight = new Light.Distant();
            redLight.setColor(Color.RED);

            Lighting redEffect = new Lighting();
            redEffect.setLight(redLight);
            redEffect.setSurfaceScale(0.0);
            redEffect.setContentInput(monochrome);

            Light.Distant greenLight = new Light.Distant();
            greenLight.setColor(Color.LIME);

            Lighting greenEffect = new Lighting();
            greenEffect.setLight(greenLight);
            greenEffect.setSurfaceScale(1.0);
            greenEffect.setContentInput(monochrome);

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

            img.effectProperty().bind(
                    Bindings
                            .when(preview.peutProperty())
                            .then((Effect) greenEffect)
                            .otherwise((Effect) redEffect)
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
