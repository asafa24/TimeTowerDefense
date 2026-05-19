package universite_paris8.iut.bak.timetowerdefense.modele;

import javafx.collections.ObservableList;

public class Jeu {
    private ObservableList<Ennemi> ennemis;
    public Jeu() {

    }



    public ObservableList<Ennemi> getEnnemi() {
        return ennemis ;
    }
    public void addEnnemi(Ennemi ennemi) {
        ennemis.add(ennemi);
    }

    public void tick(){
        for (Ennemi e : ennemis) {
            e.avancer();
        }
    }


}
