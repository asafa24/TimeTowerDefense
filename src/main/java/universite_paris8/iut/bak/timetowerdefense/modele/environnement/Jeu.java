package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileCercle;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

public class Jeu {
    private ObservableList<Ennemi> ennemis;
    private ObservableList<Defense> defenses;
    private ObservableList<Projectile> projectiles;

    private Level level;
    private int epoqueActuel;


    private int solde;
    private int pvBase;
    private int typeTourSelectionne ;


    public Jeu() {
        this.ennemis =FXCollections.observableArrayList();
        this.defenses = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        this.level = new Level();
        this.solde = 1000;
        this.pvBase = 3;
        this.epoqueActuel = 0;
        typeTourSelectionne = 0 ;
    }


    public ObservableList<Ennemi> getEnnemi() {
        return ennemis ;
    }
    public void addEnnemi(Ennemi ennemi) {
        ennemis.add(ennemi);
    }
    public void removeEnnemi(Ennemi ennemi) {
        ennemis.remove(ennemi);
    }

    public ObservableList<Defense> getDefenses() {
        return defenses;
    }
    public void addDefense(Defense defense) {
        defenses.add(defense);
    }
    public void removeDefense(Defense defense) {
        defenses.remove(defense);
    }

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }



    public void tick() {
        if (!perdu()) {
            for (Defense d : defenses) {
                if (d instanceof Tour) {
                    ((Tour) d).attaquer(ennemis, projectiles);
                }
            }
            if (!projectiles.isEmpty()) {
                for (int i = projectiles.size() - 1; i >= 0; i--) {
                    Projectile p = projectiles.get(i);
                    if (p.aAtteintCible()) {
                        if (p instanceof ProjectileCercle){
                            ProjectileCercle pCercle = (ProjectileCercle) p ;
                            double epicentreX = pCercle.getX();
                            double epicentreY = pCercle.getY();
                            for (Ennemi e : ennemis){
                                double distanceExplosion = Math.hypot(epicentreX - e.getCentreX(), epicentreY - e.getCentreY());
                                if (distanceExplosion <= pCercle.getRayonExplosion()){
                                    e.recevoirDegats(pCercle.getDegats());
                                }
                            }


                        } else if (p.getCible() != null && !p.getCible().estMort()) {
                            p.getCible().recevoirDegats(p.getDegats());
                        }
                        projectiles.remove(i);
                    }
                    p.deplacer();
                }
            }

            if (!ennemis.isEmpty()) {
                for (int i = ennemis.size() - 1; i >= 0; i--) {
                    Ennemi e = ennemis.get(i);
                    if (e.estMort()) {
                        solde += e.getRecompense();
                        ennemis.remove(i);
                        continue;
                    }

                    if (e.aAtteintLaBase()) {
                        solde += e.getRecompense();
                        ennemis.remove(i);
                        pvBase -= e.getPv();
                        continue;
                    }

                    e.avancer();
                }
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
            System.out.println(" la case " + caseX + ", " + caseY + " est occupee ou invalide ");
        }
    }
    public void poserPiege(Defense piege ){
        addDefense(piege);
        System.out.println("pose : x :"+ piege.getX() +"  y : " + piege.getY() );

    }
    private void selectionTour(){
        this.typeTourSelectionne = 1;
    }
    private void selectionTourCercle(){
        this.typeTourSelectionne = 1;
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
