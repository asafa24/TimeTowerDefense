package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileCercle;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileStun;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.List;

public class Jeu {
    private ObservableList<Ennemi> ennemis;
    private ObservableList<Defense> defenses;
    private ObservableList<Projectile> projectiles;
    private int frame;
    private List<Point2D> route ;

    private Level level;
    private int epoqueActuel;


    private IntegerProperty solde;
    private IntegerProperty pvBase;

    private int typeTourSelectionne ;

    private Vague vague;
    private int delay;
    private final int TEMPS_VAGUE_1 = 220 ;
    private int delay_entreVague;


    public Jeu() {
        this.ennemis =FXCollections.observableArrayList();
        this.defenses = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        this.level = new Level();
        this.solde = new SimpleIntegerProperty(150);
        this.pvBase = new SimpleIntegerProperty(50);
        this.epoqueActuel = 0;
        this.frame = 0;
        typeTourSelectionne = 0 ;
        this.route = level.calculerChemin(0, new Point2D(0, 9), new Point2D(10, 1));
        this.vague = new Vague();
        this.delay = 0;
        this.delay_entreVague = 80;
    }

     public Vague getVague() {
        return vague;
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
                            if (p instanceof ProjectileStun){
                                ProjectileStun pS = (ProjectileStun) p ;
                                pS.getCible().modifStun(pS.getDureeStun());
                            }
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
                        ajouterArgent(e.getRecompense());
                        System.out.println("+" + e.getRecompense() + "$");
                        ennemis.remove(i);
                        continue;
                    }

                    if (e.aAtteintLaBase()) {
                        ajouterArgent(e.getRecompense());
                        ennemis.remove(i);
                        pvBase.set(pvBase.get() - e.getPv());
                        continue;
                    }

                    e.avancer();
                }
            }
        }
        if (frame%delay_entreVague == 0 && !vague.getQueue().isEmpty() && frame > TEMPS_VAGUE_1 ) {

            addEnnemi(creerEnnemi(vague.defiler() ));


        }
        else{
            if (delay > 600 ){
                delay = 0;
                vague.vagueSuivante();
                delay_entreVague -= 8;
            }
            else {
                if (vague.getQueue().isEmpty()){
                    delay++;
                }

            }
        }
        frame++;
    }


    public void poserTour(Tour tour) {

        int caseX = (int) tour.getX();
        int caseY = (int) tour.getY();

        if (peuxPoserTour(this.epoqueActuel, tour)) {
            depenserArgent(tour.getCout());
            addDefense(tour);

            System.out.println("Tour posée : " + caseX + ", " + caseY + " -" + tour.getCout());
            System.out.println("Solde actuelle : " + this.solde.get());
        } else {
            System.out.println(" la case " + caseX + ", " + caseY + " est occupee ou invalide ou argent insuffisant");
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





    public boolean peuxPoserTour(int epoque, Tour tour ) {
        int[][] grille = level.loadLevel(epoque);

        int caseX = (int) tour.getX();
        int caseY = (int) tour.getY();

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
        return this.solde.get() >= tour.getCout();
    }

    public boolean peuxPoserPiege(int epoque, int x, int y){
        int[][] test = level.loadLevel(epoque);

        // si en dehors du terrain
        if (y < 0 || y >= test.length || x < 0 || x >= test[y].length) {
            return false;
        }

        // si les coordonnes x et y coresponde au coordonnes d'un enemi deja present retourne faux
        for (int i = 0; i < defenses.size(); i++) {
            if (defenses.get(i).getX() == x && defenses.get(i).getY() == y){
                return false;
            }
        }
        return test[y][x] >= 0 && test[y][x] <= 6 ;
    } // ok

    public void ajouterArgent(int somme) {
        this.solde.set(this.solde.get() + somme);
    }

    public void depenserArgent(int somme){
        if(solde.get() >= somme) solde.set(solde.get() - somme);
    }

    public boolean perdu(){
        return pvBase.get() <= 0;
    }

    public int getSolde() {
        return solde.get();
    }
    public IntegerProperty getSoldeProperty(){
        return solde;
    }

    public int getPvBase() {
        return pvBase.get();
    }
    public IntegerProperty getPvBaseProperty(){
        return pvBase;
    }

    private Ennemi creerEnnemi( int id ) {
        switch (id) {
            case 0 :
                return new Ennemi(0, 64 * 9, 25, 2, 5, route);
            case 1 :
                return new Ennemi(0, 64 * 9, 40, 4, 20, route);
            case 2 :
                return new Ennemi(0, 64 * 9, 150, 1, 30, route);
            default :
                return new Ennemi(0, 64 * 9, 500, 1, 400, route);

        }

    }
}
