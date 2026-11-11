package universite_paris8.iut.bak.timetowerdefense.modele.entites.base.effets;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effets;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

public class EffetBouclier implements Effets {
    private boolean applique = false;
    @Override
    public void effetAplication(Ennemi e) {
        if (!e.possedeBouclier()) {
            e.ajouterPv((int) Math.floor(e.getPv() * 1.2));
            e.setPossedeBouclier(true);
        }
        this.applique = true;

    }

    @Override
    public void tick(Ennemi e) {

    }

    @Override
    public boolean estFini() {
        return this.applique;
    }
}
