package universite_paris8.iut.bak.timetowerdefense.modele.competences;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.effets.EffetBrulure;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteEnnemi;

public class PluieMeteorites extends Ultime {

    public PluieMeteorites() {
        super(100);
    }

    @Override
    public void activerUlt(AffecteEnnemi epoque) {
        dureeRestante = 300;
        epoque.affecterEnnemis(e -> e.recevoirDegats(500));
    }

    @Override
    public void effetPersistant(AffecteEnnemi epoque) {
        epoque.affecterEnnemis(e -> e.ajouterEffet(new EffetBrulure()));
    }
}
