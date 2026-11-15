package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.collections.ListChangeListener;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Entite;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.vue.EntiteVue;

public class EcouteEntite implements ListChangeListener<Entite> {

    private EntiteVue entiteVue;
    private GestionnaireAudio audio;

    public EcouteEntite(EntiteVue entiteVue, GestionnaireAudio audio) {
        this.entiteVue = entiteVue;
        this.audio = audio;
    }

    @Override
    public void onChanged(Change<? extends Entite> ch) {
        while(ch.next()){
            if(ch.wasAdded()){
                for(Entite e : ch.getAddedSubList()) {
                    entiteVue.creerSprite(e);

                    if (e instanceof Projectile) {
                        audio.jouerSonTir((Projectile) e);
                    }
                }
            }

            if (ch.wasRemoved()){
                for(Entite e : ch.getRemoved()) {
                    entiteVue.supprimerSprite(e);
                }
            }
        }
    }
}