package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourCercle;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourStun;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Level;
import universite_paris8.iut.bak.timetowerdefense.vue.EntiteVue;
import universite_paris8.iut.bak.timetowerdefense.vue.PreviewVue;
import universite_paris8.iut.bak.timetowerdefense.vue.TerrainVue;
import universite_paris8.iut.bak.timetowerdefense.vue.UIVue;

import java.net.URL;
import java.util.ResourceBundle;

public class PropertiController {

    @FXML private Button button_ameliorez;
    @FXML private Button button_vendre;
    @FXML private ImageView p_image;
    @FXML private Label p_nom;
    @FXML private Label p_lv;
    @FXML private Label p_current_degat;
    @FXML private Label p_new_degat;
    @FXML private Label p_current_porte;
    @FXML private Label p_new_porte;
    @FXML private Label p_current_cadence;
    @FXML private Label p_new_cadence;
    @FXML private Label p_prix_upgrade;
    @FXML private Label p_prix_vente;


    private Jeu jeu;
    private Tour tourActuelle;
    private UIVue uiVue;


    public void updateStats(Tour tour, Jeu jeu) {
        this.tourActuelle = tour;
        this.uiVue = new UIVue();
        this.jeu = jeu;

        uiVue.setImageEtNom(p_image, tour, p_nom);

        p_lv.setText(String.valueOf(tour.getNiveau() + 1));

        p_current_degat.setText(String.valueOf(tour.getDegats()));
        p_current_porte.setText(String.valueOf(tour.getPortee()));
        p_current_cadence.setText(String.valueOf(tour.getCadence()));

        int futursDegats = (int) (tour.getDegats() * 1.2);
        int futurePorte = tour.getPortee() + 20;
        int futureCadence = (int) (tour.getCadence() / 1.10);

        p_new_degat.setText(String.valueOf(futursDegats));
        p_new_porte.setText(String.valueOf(futurePorte));
        p_new_cadence.setText(String.valueOf(futureCadence));

        int prixUpgrade = (int) (tour.getCout() * 2);
        int prixVente = (int) (tour.getCout() * 0.75);

        p_prix_upgrade.setText(String.valueOf(prixUpgrade));
        p_prix_vente.setText(String.valueOf(prixVente));
    }

    @FXML
    public void gereVentes() {
        if (this.tourActuelle != null && this.jeu != null) {

            int montantRevente = Integer.parseInt(p_prix_vente.getText());

            jeu.ajouterArgent(montantRevente);

            jeu.getDefenses().remove(this.tourActuelle);

            System.out.println(p_nom.getText() + " vendue pour " + montantRevente + " pièces !");

            p_nom.getScene().lookup("#zoneStats").setVisible(false);
        }
    }
    @FXML
    public void ameliorationT() {
        if (this.tourActuelle != null && this.jeu != null) {
            int montantUpgrade = Integer.parseInt(p_prix_upgrade.getText());
            if (jeu.getSoldeProperty().get() >= montantUpgrade) {
                jeu.ajouterArgent(-montantUpgrade);
                tourActuelle.amelioration();
                updateStats(tourActuelle, jeu);
            } else {
                System.out.println("Pas assez d'or !");
            }
        }
    }

}