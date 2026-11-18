package universite_paris8.iut.bak.timetowerdefense.modele.entites.base.effets;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effets;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

public class EffetBrulure implements Effets {
    private int dureeRestante = 60 ;
    @Override
    public void effetAplication(Ennemi e) {

    }

    @Override
    public void tick(Ennemi e) {
        this.dureeRestante--;
        if (this.dureeRestante % 10 == 0) {
            e.recevoirDegats(1);
        }
    }

    @Override
    public boolean estFini() {
        return this.dureeRestante <= 0;
    }
}
