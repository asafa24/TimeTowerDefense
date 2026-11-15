package universite_paris8.iut.bak.timetowerdefense.modele.entites.effets;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

public interface EffetStatut {
    int getDuree();

    default double modifierVitesse(double vitesse) {
        return vitesse;
    }

    default void tick(Ennemi ennemi, int dureeRestante) {
    }

    default void surApplication(Ennemi ennemi) {
    }
}