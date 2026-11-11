package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
public interface Effets {
    void effetAplication(Ennemi e);

    void tick(Ennemi e);

    boolean estFini();
}
