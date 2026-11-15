package universite_paris8.iut.bak.timetowerdefense.modele.entites.effets;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

public class EffetBouclier implements EffetStatut {
    @Override
    public int getDuree() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void surApplication(Ennemi ennemi) {
        ennemi.ajouterPv((int) Math.floor(ennemi.getPv() * 1.2));
    }
}