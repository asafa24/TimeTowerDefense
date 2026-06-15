package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;
import universite_paris8.iut.bak.timetowerdefense.vue.UIVue;

public class ControleurStatsTour {

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
    private ControleurJeu controleurJeu ;


    public void updateStats(Tour tour, Jeu jeu ,ControleurJeu controleurJeu) {
        this.tourActuelle = tour;
        this.uiVue = new UIVue();
        this.jeu = jeu;
        this.controleurJeu = controleurJeu;

        uiVue.setImageEtNom(p_image, tour, p_nom ,jeu.getEpoqueActuel());

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

            System.out.println(p_nom.getText() + " vendue pour " + montantRevente + " ecus");

            if (this.controleurJeu != null) {
                this.controleurJeu.fermerMenuTour();
            }
        }
    }
    @FXML
    public void ameliorationT() {
        if (tourActuelle.getNiveau() > 2 ){
            controleurJeu.afficherMessage("cette tour a atteint son niveau maxi !",Color.GOLD , 1);
        }else {
            if (this.tourActuelle != null && this.jeu != null) {
                int montantUpgrade = Integer.parseInt(p_prix_upgrade.getText());
                if (jeu.getSoldeProperty().get() >= montantUpgrade) {
                    jeu.ajouterArgent(-montantUpgrade);
                    tourActuelle.amelioration();
                    updateStats(tourActuelle, jeu, this.controleurJeu);
                } else {
                    controleurJeu.afficherMessage("Pas assez d'or !", Color.RED, 1);
                }
            }
        }
    }

}