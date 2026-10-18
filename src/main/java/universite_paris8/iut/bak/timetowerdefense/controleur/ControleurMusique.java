package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import universite_paris8.iut.bak.timetowerdefense.Application;

import java.net.URL;

/** Centralise la musique d'ambiance et les réglages audio de l'interface. */
public class ControleurMusique {
    private final GestionnaireAudio gestionnaireAudio;
    private final Slider sliderMusique;
    private final Button btnMusique;
    private final Button btnSfx;

    private MediaPlayer musiqueAmbiance;
    private boolean muteSfx;

    public ControleurMusique(GestionnaireAudio gestionnaireAudio, Slider sliderMusique, Slider sliderSfx,
            Button btnMusique, Button btnSfx) {
        this.gestionnaireAudio = gestionnaireAudio;
        this.sliderMusique = sliderMusique;
        this.btnMusique = btnMusique;
        this.btnSfx = btnSfx;

        sliderMusique.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (musiqueAmbiance != null) {
                musiqueAmbiance.setVolume(newVal.doubleValue());
            }
        });
        sliderSfx.valueProperty().addListener((obs, oldVal, newVal) -> {
            this.gestionnaireAudio.setVolume(newVal.doubleValue());
        });
    }

    public GestionnaireAudio getGestionnaireAudio() {
        return gestionnaireAudio;
    }

    public void lancerMusique(int epoque) {
        if (musiqueAmbiance != null) {
            musiqueAmbiance.stop();
        }

        String cheminMusique = epoque == 1 ? "media/antiquite.mp3" : "media/prehistoire.mp3";
        URL url = Application.class.getResource(cheminMusique);
        if (url != null) {
            musiqueAmbiance = new MediaPlayer(new Media(url.toExternalForm()));
            musiqueAmbiance.setVolume(0.2);
            musiqueAmbiance.setCycleCount(MediaPlayer.INDEFINITE);
            musiqueAmbiance.play();
        }
    }

    public void jouerSonSpecial(String cle) {
        gestionnaireAudio.jouerSonSpecial(cle);
    }

    public void toggleMuteMusique() {
        if (musiqueAmbiance != null) {
            boolean estMuet = !musiqueAmbiance.isMute();
            musiqueAmbiance.setMute(estMuet);
            btnMusique.setText(estMuet ? "🔇 " : "🔊 ");
        }
    }

    public void toggleMuteSfx() {
        muteSfx = !muteSfx;
        gestionnaireAudio.setMute(muteSfx);
        btnSfx.setText(muteSfx ? "🔇 " : "🔊 ");
    }
}
