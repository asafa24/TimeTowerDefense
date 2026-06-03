package universite_paris8.iut.bak.timetowerdefense.modele.competences;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.List;

public class PluieMeteorites extends Ultime {

    public PluieMeteorites() {
        super(100);
    }

    @Override
    public void activerUlt(List<Ennemi> ennemis) {
        dureeRestante = 300;
        for (Ennemi e : ennemis) {
            e.recevoirDegats(500);
        }
    }

    @Override
    public void effetPersistant(List<Ennemi> ennemis) {
        for (Ennemi e : ennemis) {
            e.appliqueEffet(Effet.BURN);
        }
    }
}
