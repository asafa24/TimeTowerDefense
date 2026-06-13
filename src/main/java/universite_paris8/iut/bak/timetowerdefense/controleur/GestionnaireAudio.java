package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.scene.media.AudioClip;
import universite_paris8.iut.bak.timetowerdefense.Application;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.projectiles.FlechetteEmpoisonne;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Caillou;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Filet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Fleche;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GestionnaireAudio {

    private Map<Class<? extends Projectile>, AudioClip> sonsTirs;
    private Map<String, AudioClip> sonsSpeciaux;
    private double volumeSfx = 0.2;
    private boolean mute = false;

    public GestionnaireAudio() {
        sonsTirs = new HashMap<>();
        sonsSpeciaux = new HashMap<>();
        chargerSons();
    }

    private void chargerSons() {
        try {

            ajouterSon(Fleche.class, "media/sfx_arc.mp3");
            ajouterSon(Caillou.class, "media/sfx_caillou.mp3");
            ajouterSon(Filet.class, "media/sfx_filet.mp3");
            ajouterSon(FlechetteEmpoisonne.class, "media/sfx_arc.mp3");

            ajouterSonSpecial("cri_boss", "media/sfx_rawr.mp3");
            ajouterSonSpecial("meteore", "media/sfx_meteore.mp3");
            ajouterSonSpecial("tornado", "media/sfx_tornado.mp3");


            // Exemples pour la suite :
            // ajouterSon(Jar.class, "media/sfx_jarre.mp3");
            // ajouterSon(BouleDeFeu.class, "media/sfx_mage.mp3");
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des sons SFX : " + e.getMessage());
        }
    }

    private void ajouterSon(Class<? extends Projectile> classeProjectile, String cheminRelatif) {
        URL url = Application.class.getResource(cheminRelatif);
        if (url != null) {
            sonsTirs.put(classeProjectile, new AudioClip(url.toExternalForm()));
        } else {
            System.err.println("Fichier audio introuvable : " + cheminRelatif);
        }
    }

    private void ajouterSonSpecial(String cle, String cheminRelatif){
        URL url = Application.class.getResource(cheminRelatif);
        if (url != null) sonsSpeciaux.put(cle, new AudioClip(url.toExternalForm()));
    }

    public void jouerSonSpecial(String cle){
        if(mute) return;
        AudioClip son = sonsSpeciaux.get(cle);
        if(son != null) son.play(volumeSfx);
    }

    public void jouerSonTir(Projectile p) {
        if (mute) return; // Si le jeu est en sourdine, on bloque la lecture

        AudioClip son = sonsTirs.get(p.getClass());
        if (son != null) {
            son.play(volumeSfx);
        }
    }

    public void setVolume(double volume) {
        // Sécurité pour garder le volume entre 0.0 et 1.0
        this.volumeSfx = Math.max(0.0, Math.min(1.0, volume));
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public boolean isMute() {
        return this.mute;
    }
}