package universite_paris8.iut.bak.timetowerdefense.modele.entites.effets;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

public class EffetBrulure implements EffetStatut {
    @Override
    public int getDuree() {
        return 60;
    }

    @Override
    public void tick(Ennemi ennemi, int dureeRestante) {
        if (dureeRestante % 10 == 0) {
            ennemi.recevoirDegats(1);
        }
    }
}