package universite_paris8.iut.bak.timetowerdefense.modele.competences;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.List;

public class TempeteDeSable extends Ultime{
    public TempeteDeSable() {
        super(100);
    }

    @Override
    public void activerUlt(List<Ennemi> ennemis) {
        for(Ennemi e : ennemis) {
            e.setX(64 * 10);
            e.setY(64 * 10);
            e.setEtapeActuelle(0);
            e.recevoirDegats(10);
        }
    }
}
