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

                if (e.aAtteintLaBase()){
                    ennemis.remove(i);
                    pvBase -= e.getPv();
                    continue;
                }

                e.avancer();
            }
        }
    }
    public void poserTour(Tour tour) {

        int caseX = (int) tour.getX();
        int caseY = (int) tour.getY();

        if (peuxPoserTour(this.epoqueActuel, caseX, caseY)) {
            addDefense(tour);
            System.out.println("Tour posée : " + caseX + ", " + caseY);
        } else {
            System.out.println("Désolé, la case " + caseX + ", " + caseY + " est occupée ou invalide !");
        }
    }
    public void poserPiege(Defense piege ){
        addDefense(piege);
        System.out.println("pose : x :"+ piege.getX() +"  y : " + piege.getY() );

    }




    public boolean peuxPoserTour(int epoque, int caseX, int caseY) {
        int[][] grille = level.loadLevel(epoque);

        if (caseY < 0 || caseY >= grille.length || caseX < 0 || caseX >= grille[caseY].length) {
            return false;
        }

        if (grille[caseY][caseX] != 0) {
            return false;
        }

        for (Defense d : defenses) {
            if ((int)d.getX() == caseX && (int)d.getY() == caseY) {
                return false;
            }
        }
        return true;
    }

    public boolean peuxPoserPiege(int epoque, int x, int y){
        int[][] test = level.loadLevel(epoque);

        // si en dehors du terrain
        if (y < 0 || y >= test.length || x < 0 || x >= test[y].length) {
            return false;
        }

        // si les coordonnes x et y coresponde au coordonnes d'un enemi deja present retourne faux
        for (int i = 0; i < defenses.size(); i++) {
            if (defenses.get(i).getX() == x && defenses.get(i).getY() == y){return false ;} ;
        }
        // si different de herbe vide retourne faux
        return test[y][x] >= 0 && test[y][x] <= 6 ;
    } // ok

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
