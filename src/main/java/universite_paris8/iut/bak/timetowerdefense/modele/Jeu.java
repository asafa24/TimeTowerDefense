package universite_paris8.iut.bak.timetowerdefense.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Jeu {
    private ObservableList<Ennemi> ennemis;
    private ObservableList<Defense> defenses;

    private Level level;
    private int epoqueActuel;

    private int solde;
    private int pvBase;


    public Jeu() {
        this.ennemis =FXCollections.observableArrayList();
        this.defenses = FXCollections.observableArrayList();
        this.level = new Level();
        this.solde = 1000;
        this.pvBase = 100;
        this.epoqueActuel = 0;
    }


    public ObservableList<Ennemi> getEnnemi() {
        return ennemis ;
    }
    public void addEnnemi(Ennemi ennemi) {
        ennemis.add(ennemi);
    }
    public void removeEnnemi(Ennemi ennemi) {ennemis.remove(ennemi);}

    public ObservableList<Defense> getDefenses() {return defenses;}
    public void addDefense(Defense defense) {defenses.add(defense);}
    public void removeDefense(Defense defense) {defenses.remove(defense);}

    public void tick() {
        if (!ennemis.isEmpty()) {
            for (int i = ennemis.size()-1; i >= 0; i--) {
                Ennemi e = ennemis.get(i);
                if(e.estMort()){
                    ennemis.remove(i);
                    continue;
                }
                e.avancer();
            }
        }
    }

    public boolean peuxPoserTour(int epoque, int x, int y){
        return true;
    }

    public boolean peuxPoserPiege(int epoque, int x, int y){
        return true;
    }

    public void ajouterArgent(int somme) {
        this.solde += somme;
    }

    public void depenserArgent(int somme){
        if(solde >= somme) solde -= somme;
    }

    public boolean perdu(){
        return pvBase <= 0;
    }

}
