package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import java.util.function.Consumer;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

public interface AffecteEnnemi {

    public void affecterEnnemis(Consumer<Ennemi> consumer);

    void addEnnemi(Ennemi e);
}
