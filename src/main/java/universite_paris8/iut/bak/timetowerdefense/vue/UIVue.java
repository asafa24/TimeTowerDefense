package universite_paris8.iut.bak.timetowerdefense.vue;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.glossaire.CategorieGlossaire;
import universite_paris8.iut.bak.timetowerdefense.modele.glossaire.EpoqueGlossaire;
import universite_paris8.iut.bak.timetowerdefense.modele.glossaire.EntreeGlossaire;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.PistoletLaser;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.PistoletLazerMk2;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.Tesla;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.TourDuTemps;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def.*;

public class UIVue {

    public UIVue() {
    }

    public StringBinding setPrixT1(int epoque, int id) {
        int prix;
        switch (epoque) {
            case 0 -> prix = switch (id) {
                case 0 -> MiniVolcan.cout.get();
                case 1 -> ArbreRuste.getPrixBase();
                case 2 -> CatapulteCaillou.getPrixBase();
                case 3 -> LanceFilet.cout.get();
                default -> 999;
            };
            case 1 -> prix = switch (id) {
                case 0 -> PorteDeSable.getPrixBase();
                case 1 -> TotemFlechette.getPrixBase();
                case 2 -> CatapulteJAR.getPrixBase();
                case 3 -> PyramideShooteuse.getPrixBase();
                default -> 999;
            };
            case 2 -> prix = switch (id) {
                case 0 -> ElPrimo.getPrixBase();
                case 1 -> Archer.getPrixBase();
                case 2 -> LanceBuche.getPrixBase();
                case 3 -> TourMage.getPrixBase();
                default -> 999;
            };
            case 3 -> prix = switch (id) {
                case 0 -> Mine.getPrixBase();
                case 1 -> SoldatBleu.getPrixBase();
                case 2 -> Sniper.getPrixBase();
                case 3 -> LanceRocket.getPrixBase();
                default -> 999;
            };
            case 4 -> prix = switch (id) {
                case 0 -> PistoletLaser.getPrixBase();
                case 1 -> PistoletLazerMk2.getPrixBase();
                case 2 -> Tesla.getPrixBase();
                case 3 -> TourDuTemps.getPrixBase();
                default -> 999;
            };
            default -> prix = 999;
        }
        return new SimpleIntegerProperty(prix).asString();
    }

    public ImageView setImageT1(int epoque, int button) {
        String epoque_txt;
        switch (epoque) {
            case 1: epoque_txt = "antiquite/"; break;
            case 2: epoque_txt = "moyen-age/"; break;
            case 3: epoque_txt = "contemporain/"; break;
            case 4: epoque_txt = "future/"; break;
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

    public void afficherGlossaire(EpoqueGlossaire epoque, VBox contenuGlossaire) {
        contenuGlossaire.getChildren().clear();

        Label titre = new Label(epoque.getNom());
        titre.getStyleClass().add("glossary-title");
        Label sousTitre = new Label("GLOSSAIRE  ·  " + epoque.getEntrees().size() + " unités répertoriées");
        sousTitre.getStyleClass().add("glossary-subtitle");
        contenuGlossaire.getChildren().addAll(titre, sousTitre);

        for (EntreeGlossaire entree : epoque.getEntrees()) {
            ajouterEntreeGlossaire(entree, contenuGlossaire);
        }
    }

    private void ajouterEntreeGlossaire(EntreeGlossaire entree, VBox contenuGlossaire) {
        Label lblNom = new Label(entree.getNom());
        lblNom.getStyleClass().add("glossary-entry-name");
        lblNom.setWrapText(true);

        Label categorie = new Label(nomCategorie(entree.getCategorie()));
        categorie.getStyleClass().addAll("glossary-category", classeCategorie(entree.getCategorie()));

        HBox entete = new HBox(8, lblNom, categorie);
        entete.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        HBox.setHgrow(lblNom, Priority.ALWAYS);

        Label description = new Label(entree.getDescription());
        description.getStyleClass().add("glossary-entry-description");
        description.setWrapText(true);
        description.setMaxWidth(Double.MAX_VALUE);

        VBox carte = new VBox(7, entete, description);
        carte.getStyleClass().add("glossary-card");
        contenuGlossaire.getChildren().add(carte);
    }

    private String nomCategorie(CategorieGlossaire categorie) {
        return switch (categorie) {
            case DEFENSE -> "DÉFENSE";
            case ENNEMI -> "ENNEMI";
            case ENNEMI_RESISTANT -> "RÉSISTANT";
            case BOSS -> "BOSS";
        };
    }

    private String classeCategorie(CategorieGlossaire categorie) {
        return switch (categorie) {
            case DEFENSE -> "category-defense";
            case ENNEMI -> "category-enemy";
            case ENNEMI_RESISTANT -> "category-resistant";
            case BOSS -> "category-boss";
        };
    }
    private Color couleurPour(CategorieGlossaire categorie) {
        return switch (categorie) {
            case DEFENSE -> Color.WHITE;
            case ENNEMI -> Color.LIGHTCORAL;
            case ENNEMI_RESISTANT -> Color.ORANGE;
            case BOSS -> Color.RED;
        };
    }
}

