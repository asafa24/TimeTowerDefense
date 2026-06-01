package universite_paris8.iut.bak.timetowerdefense.controleur;

import javafx.collections.ListChangeListener;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Entite;
import universite_paris8.iut.bak.timetowerdefense.vue.EntiteVue;

public class EcouteEntite implements ListChangeListener<Entite> {

    private EntiteVue entiteVue;

    public EcouteEntite(EntiteVue entiteVue) {
        this.entiteVue = entiteVue;
    }

    @Override
    public void onChanged(Change<? extends Entite> ch) {
        while(ch.next()){
            if(ch.wasAdded()){
                for(Entite e : ch.getAddedSubList()) entiteVue.creerSprite(e);
            }

            if (ch.wasRemoved()){
                for(Entite e : ch.getRemoved()) entiteVue.supprimerSprite(e);
            }
        }
    }
}
