package universite_paris8.iut.bak.timetowerdefense.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Jeu {
    private ObservableList<Ennemi> ennemis;
    public Jeu() {
        this.ennemis =FXCollections.observableArrayList();
    }



    public ObservableList<Ennemi> getEnnemi() {
        return ennemis ;
    }
    public void addEnnemi(Ennemi ennemi) {
        ennemis.add(ennemi);
    }

    public void tick() {
        if (!ennemis.isEmpty()) {

            for (Ennemi e : ennemis) {
                e.avancer();
            }
        }
    }


}
