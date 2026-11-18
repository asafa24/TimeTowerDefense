package universite_paris8.iut.bak.timetowerdefense.modele.competences;

import universite_paris8.iut.bak.timetowerdefense.modele.environnement.AffecteEnnemi;

public abstract class Ultime {

    protected int compteurKill;
    protected int dureeRestante;

    public Ultime(int compteurKill) {
        this.compteurKill = compteurKill;
        this.dureeRestante = 0;

    }

    public int getCompteurKill() {
        return compteurKill;
    }

    public abstract void activerUlt(AffecteEnnemi epoque);

    public void effetPersistant(AffecteEnnemi epoque) {
    }

    public void agir(AffecteEnnemi epoque) {
        if (dureeRestante > 0) {
            effetPersistant(epoque);
            dureeRestante--;
        }
    }
}
