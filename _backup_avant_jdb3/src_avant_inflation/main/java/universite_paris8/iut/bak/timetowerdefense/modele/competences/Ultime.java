package universite_paris8.iut.bak.timetowerdefense.modele.competences;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

import java.util.List;

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

    public abstract void activerUlt(List<Ennemi> ennemis);

    public void effetPersistant(List<Ennemi> ennemis){}

    public void tick(List<Ennemi> ennemis){
        if(dureeRestante > 0){
            effetPersistant(ennemis);
            dureeRestante--;
        }
    }
}
