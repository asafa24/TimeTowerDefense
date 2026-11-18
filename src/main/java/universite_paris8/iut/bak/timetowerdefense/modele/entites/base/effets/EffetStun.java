package universite_paris8.iut.bak.timetowerdefense.modele.entites.base.effets;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effets;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

public class EffetStun implements Effets {
    private int dureeRestante = 120;

    @Override
    public void effetAplication(Ennemi e) {

    }

    @Override
    public void tick(Ennemi e) {
        this.dureeRestante--;
        e.setVitesseActuelle(0);
    }

    @Override
    public boolean estFini() {
        return this.dureeRestante <= 0;
    }
}
