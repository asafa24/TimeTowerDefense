package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.beans.binding.StringBinding;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.CatapulteJAR;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.PorteDeSable;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.PyramideShooteuse;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.TotemFlechette;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.ArbreRuste;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.CatapulteCaillou;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.LanceFilet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.MiniVolcan;

public class UIVue {

    public UIVue() {
    }


    public StringBinding setPrixT1(int epoque, int id) {
        switch (epoque) {
            case 0: //  Prehistoire
                switch (id) {
                    case 0:
                        return MiniVolcan.coutPropertyMiniVolcan().asString();
                    case 1:
                        return ArbreRuste.coutPropertyArbre().asString();
                    case 2:
                        return CatapulteCaillou.coutPropertyCatapulteCaillou().asString();
                    case 3:
                        return LanceFilet.coutPropertyLanceFilet().asString();
                }
                break;

            case 1: // epoque Antiquité
                switch (id) {
                    case 0:
                        return PorteDeSable.coutPropertyPorteSable().asString();
                    case 1:
                        return TotemFlechette.coutPropertyTotemFlechette().asString();
                    case 2:
                        return CatapulteJAR.coutPropertyCatapulteJar().asString();
                    case 3:
                        return PyramideShooteuse.coutPropertyPyramideShooteuse().asString();
                }
                break;
        }
        return null;
    }
    public ImageView setImageT1(int epoque, int button){
        String epoque_txt;
        switch (epoque){
            case 1:
                epoque_txt = "antiquite/";
                break;
            default:
                epoque_txt = "prehistoire/";
                break;
        }
        Image img ;
        try {
             img = new Image(String.valueOf(Application.class.getResource("images/tiles/" + epoque_txt + "button/b_0" + button + ".png")));
        }
        catch (Exception e){
             img = new Image(String.valueOf(Application.class.getResource("images/tiles/noTexture.png")));
        }
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(64);
        imageView.setFitWidth(64);
        imageView.setPreserveRatio(true);
        return imageView;
    }
    public void setImageEtNom(ImageView p_image, Tour tour, Label p_nom , int epoqueActuelle){
        switch (epoqueActuelle){
            case 0:
                if (tour instanceof CatapulteCaillou) {
                p_nom.setText("Lance-pierre");
                p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/catapulteT2.png"))));
                } else if (tour instanceof LanceFilet) {
                    p_nom.setText("Filetoorr");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/LanceFilet.png"))));
                } else {
                    p_nom.setText("Arbre rustre");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/prehistoire/def/arbre.png"))));
                }
                break;
            case 1:
                if (tour instanceof TotemFlechette) {
                    p_nom.setText("Totem empoisonné");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/tour/piegeFlechette.png"))));
                } else if (tour instanceof CatapulteJAR) {
                    p_nom.setText("CataJar");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/tour/catapulteJar.png"))));
                } else {
                    p_nom.setText("Pyramidor");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/antiquite/def/tour/piramidJ.png"))));
                }

        }





    }
}