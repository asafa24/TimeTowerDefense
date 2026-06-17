package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.futur.def.PistoletLaser;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.futur.def.PistoletLazerMk2;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def.*;

public class UIVue {

    public UIVue() {
    }

    public StringBinding setPrixT1(int epoque, int id) {
        switch (epoque) {
            case 0: // Prehistoire
                switch (id) {
                    case 0: return MiniVolcan.coutPropertyMiniVolcan().asString();
                    case 1: return ArbreRuste.coutPropertyArbre().asString();
                    case 2: return CatapulteCaillou.coutPropertyCatapulteCaillou().asString();
                    case 3: return LanceFilet.coutPropertyLanceFilet().asString();
                }
                break;

            case 1: // Antiquité
                switch (id) {
                    case 0: return PorteDeSable.coutPropertyPorteSable().asString();
                    case 1: return TotemFlechette.coutPropertyTotemFlechette().asString();
                    case 2: return CatapulteJAR.coutPropertyCatapulteJar().asString();
                    case 3: return PyramideShooteuse.coutPropertyPyramideShooteuse().asString();
                }
                break;

            case 2: // Moyen-Âge (Prix factices pour compiler direct)
                switch (id) {
                    case 0: return new SimpleIntegerProperty(50).asString(); // El Primo
                    case 1: return new SimpleIntegerProperty(100).asString(); // Archer
                    case 2: return new SimpleIntegerProperty(150).asString(); // LanceBuche
                    case 3: return new SimpleIntegerProperty(200).asString(); // TourMage
                }
                break;

            case 3: // Contemporain (Prix factices)
                switch (id) {
                    case 0: return new SimpleIntegerProperty(75).asString(); // Mine
                    case 1: return new SimpleIntegerProperty(120).asString(); // Sniper/Soldat
                    case 2: return new SimpleIntegerProperty(180).asString(); // LanceRocket
                    case 3: return new SimpleIntegerProperty(250).asString();
                }
                break;

            case 4: // Futur (Prix factices)
                switch (id) {
                    case 0: return new SimpleIntegerProperty(100).asString(); // Laser
                    case 1: return new SimpleIntegerProperty(250).asString(); // MK2
                    case 2: return new SimpleIntegerProperty(200).asString(); // Tesla
                    case 3: return new SimpleIntegerProperty(300).asString(); // Temps
                }
                break;

            default: // Fallback Epoque 0
                switch (id) {
                    case 0: return MiniVolcan.coutPropertyMiniVolcan().asString();
                    case 1: return ArbreRuste.coutPropertyArbre().asString();
                    case 2: return CatapulteCaillou.coutPropertyCatapulteCaillou().asString();
                    case 3: return LanceFilet.coutPropertyLanceFilet().asString();
                }
                break;
        }
        return new SimpleIntegerProperty(999).asString();
    }

    public ImageView setImageT1(int epoque, int button) {
        String epoque_txt;
        switch (epoque) {
            case 1: epoque_txt = "antiquite/"; break;
            case 2: epoque_txt = "moyen-age/"; break;
            case 3: epoque_txt = "contemporain/"; break;
            case 4: epoque_txt = "future/"; break; // Attention au dossier "future" !
            default: epoque_txt = "prehistoire/"; break;
        }
        Image img;
        try {
            img = new Image(String.valueOf(Application.class.getResource("images/tiles/" + epoque_txt + "button/b_0" + button + ".png")));
        } catch (Exception e) {
            img = new Image(String.valueOf(Application.class.getResource("images/tiles/noTexture.png")));
        }
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(64);
        imageView.setFitWidth(64);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public void setImageEtNom(ImageView p_image, Tour tour, Label p_nom, int epoqueActuelle) {
        switch (epoqueActuelle) {
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
                break;
            case 2:
                if (tour instanceof TourMage) {
                    p_nom.setText("Tour de Mage");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/moyen-age/def/tour/tourmage.png"))));
                } else if (tour instanceof LanceBuche) {
                    p_nom.setText("Lance Bûche");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/moyen-age/def/tour/catapulte.png"))));
                } else {
                    p_nom.setText("Archer");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/moyen-age/def/tour/archer.png"))));
                }
                break;
            case 3:
                if (tour instanceof LanceRocket) {
                    p_nom.setText("Lance-Rocket");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/contemporain/def/lancerocket.png"))));
                } else if (tour instanceof Sniper) {
                    p_nom.setText("Sniper");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/contemporain/def/sniper.png"))));
                } else {
                    p_nom.setText("Soldat");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/contemporain/def/soldatbleu.png"))));
                }
                break;
            case 4:
                if (tour instanceof PistoletLaser) {
                    p_nom.setText("Laser Classic");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/future/def/tour/laser.png"))));
                } else if (tour instanceof PistoletLazerMk2) {
                    p_nom.setText("Laser MK2");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/future/def/tour/blaster.png"))));
                } else if (tour instanceof universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.Tesla) {
                    p_nom.setText("Tour Tesla");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/future/def/tour/tesla.png"))));
                } else if (tour instanceof universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.TourDuTemps) {
                    p_nom.setText("Tour du Temps");
                    p_image.setImage(new Image(String.valueOf(Application.class.getResource("images/tiles/future/def/tour/temps.png"))));
                }
                break;
        }
    }

    public void afficherMessage(String texte, Color color, int duree, Label labelMessageSys) {
        labelMessageSys.setTextFill(color);
        labelMessageSys.setText(texte);
        labelMessageSys.setVisible(true);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.2), labelMessageSys);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        PauseTransition pause = new PauseTransition(Duration.seconds(duree));
        pause.setOnFinished(e -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), labelMessageSys);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(eventFade -> labelMessageSys.setVisible(false));
            fadeOut.play();
        });
        pause.play();
    }

    public void ajouterEntreeGlossaire(String nom, String description, Color couleurTitre ,VBox contenuGlossaire){
        Label lblNom = new Label("• " + nom);
        lblNom.setTextFill(couleurTitre);
        lblNom.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Label lblDesc = new Label(description);
        lblDesc.setTextFill(Color.LIGHTGRAY);
        lblDesc.setWrapText(true);

        VBox entree = new VBox(2, lblNom, lblDesc);
        contenuGlossaire.getChildren().add(entree);
    }
}