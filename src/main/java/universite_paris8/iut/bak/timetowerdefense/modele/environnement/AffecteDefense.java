package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import java.util.function.Consumer;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

public interface AffecteDefense {

    public void affecterDefenses(Consumer<Defense> consumer);
}
