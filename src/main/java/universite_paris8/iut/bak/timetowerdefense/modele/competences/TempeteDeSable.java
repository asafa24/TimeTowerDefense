package universite_paris8.iut.bak.timetowerdefense.modele.competences;

import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteEnnemi;

public class TempeteDeSable extends Ultime {
    public TempeteDeSable() {
        super(100);
    }

    @Override
    public void activerUlt(AffecteEnnemi epoque) {
        epoque.affecterEnnemis(e -> {
            e.setX(64 * 10);
            e.setY(64 * 10);
            e.setEtapeActuelle(0);
            e.recevoirDegats(10);
        });
    }
}
