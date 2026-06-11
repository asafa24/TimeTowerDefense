package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.PluieMeteorites;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.Ultime;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.enemie.GolemSable;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.enemie.Momie;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.enemie.Compsognathus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.enemie.Triceratops;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.enemie.Tyrannosaurus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.enemie.Velociraptor;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.preview.Preview;

import java.util.List;

public class Jeu {

    private ObservableList<Ennemi> ennemis;
    private ObservableList<Defense> defenses;
    private ObservableList<Projectile> projectiles;
    private int frame;
    private List<Point2D> route ;

    private Level level;
    private IntegerProperty epoqueActuel;
    private Preview preview = new  Preview(0.0,0.0,this,-1);

    private IntegerProperty solde;
    private IntegerProperty pvBase;
    private Vague vague;
    private int delay;
    private final int TEMPS_ENTRE_VAGUE = 220 ;
    private int delaySpawnMob = 80;
    private int id = -1;

    private Ultime ultimeActuelle;
    private IntegerProperty compteurKill;

    private boolean modeExtreme = false;

    public Jeu() {
        this.ennemis = FXCollections.observableArrayList();
        this.defenses = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        this.level = new Level();
        this.solde = new SimpleIntegerProperty(200);
        this.pvBase = new SimpleIntegerProperty(50);
        this.epoqueActuel = new SimpleIntegerProperty(0);
        this.frame = 0;
        this.route = level.calculerChemin(epoqueActuel.get(), ennemiDepart(epoqueActuel.get()), ennemiArrivee(epoqueActuel.get()));
        this.vague = new Vague();
        this.delay = 0;
        this.ultimeActuelle = new PluieMeteorites();
        this.compteurKill = new SimpleIntegerProperty(0);

    }

    public void newRoute() {
        this.route = level.calculerChemin(epoqueActuel.get(), ennemiDepart(epoqueActuel.get()), ennemiArrivee(epoqueActuel.get()));
    }

    public int getEpoqueActuel() {
        return epoqueActuel.get();
    }
    public IntegerProperty getEpoqueActuelProperty() {
        return epoqueActuel;
    }

    public void setEpoqueActuel(int epoqueActuel) {
        this.epoqueActuel.set(epoqueActuel);
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
    public void nuke(){
        ennemis.clear();
        defenses.clear();
        projectiles.clear();

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

    public boolean tick() {
        if (!perdu()) {
            ultimeActuelle.tick(ennemis);
            for (int i = defenses.size() - 1; i >= 0; i--) {
                Defense d = defenses.get(i);
                d.agir(ennemis, projectiles);
                if (d instanceof Destructible && ((Destructible) d).estMort()) {
                    defenses.remove(i);
                }
            }

            if (!projectiles.isEmpty()) {
                for (int i = projectiles.size() - 1; i >= 0; i--) {
                    Projectile p = projectiles.get(i);
                    if (p.aAtteintCible()) {
                        p.appliquerImpact(ennemis);
                        projectiles.remove(i);
                        //ici gemini
                        continue;
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
                        this.compteurKill.set(this.compteurKill.get() + 1);
                        ennemis.remove(i);
                        continue;
                    }

                    if (e.aAtteintLaBase()) {
                        ajouterArgent(e.getRecompense());
                        ennemis.remove(i);
                        this.compteurKill.set(this.compteurKill.get() + 1);
                        pvBase.set(pvBase.get() - e.getPv());
                        continue;
                    }
                    e.agir(ennemis, defenses);
                    e.avancer();
                }
            }
            if (frame % delaySpawnMob == 0 && !vague.getQueue().isEmpty() && frame > TEMPS_ENTRE_VAGUE) {
                addEnnemi(creerEnnemi(vague.defiler()));
            } else {
                if (delay > 600) {
                    delay = 0;
                    if (vague.vagueSuivante()){


                    }
                    else{
                        vague.levelSuiv();
                        prochaineEpoque();
                    }
                    if (delaySpawnMob > 16) delaySpawnMob -= 8;
                } else {
                    if (vague.getQueue().isEmpty()) {
                        delay++;
                    }
                }
            }
            frame++;
            return true;
        }
        return false;
    }

    public Point2D ennemiDepart(int epoque){
        switch(epoque){
            case 0: return new Point2D(0, 9);
            case 1: return new Point2D(10, 10);
        }

        return null;
    }

    public Point2D ennemiArrivee(int epoque){
        switch (epoque){
            case 0: return new Point2D(10, 1);
            case 1: return new Point2D(0, 2);
        }

        return null;
    }

    private Ennemi creerEnnemi(int id ) {
        switch (this.epoqueActuel.get()){
            case 0 -> {
                switch (id) {
                    case 0:
                        return new Compsognathus(route);
                    case 1:
                        return new Velociraptor(route);
                    case 2:
                        return new Triceratops(route);
                    default:
                        return new Tyrannosaurus(0, 64 * 9, 1000, 1, 400, 900, route);
                }
            }
            case 1 -> {
                switch (id) {
                    case 0:
                        return new Momie(route);
                    case 1:
                        return new GolemSable(route);
                    case 2:
                        return new Triceratops(route);
                    default:
                        return new Tyrannosaurus(0, 64 * 9, 1000, 1, 400, 900, route);
                }
            }
        }
        return null;
    }


    public void preview(double x, double y) {
        preview.update(x,y,id);
    }

    public boolean poserPiege(Piege piege ){
        int caseX = (int) (piege.getX()) ;
        int caseY = (int) (piege.getY())  ;

        if (peuxPoserPiege(this.epoqueActuel.get(), piege)) {
            depenserArgent(piege.getCout());
            addDefense(piege);
            System.out.println("Piège posé : " + caseX + ", " + caseY + " -" + piege.getCout());
            return true;
        } else {
            System.out.println(" la case " + caseX + ", " + caseY + " est occupee ou invalide ou argent insuffisant");
            return false;
        }
    }
    public boolean peuxPoserPiege(int epoque, Piege piege){
        int[][] test = level.loadLevel(epoque);
        int x = (int) piege.getX();
        int y = (int) piege.getY();

        if (y < 0 || y >= test.length || x < 0 || x >= test[y].length) return false;

        for (int i = 0; i < defenses.size(); i++) {
            if (defenses.get(i).getX() == x && defenses.get(i).getY() == y) return false;
        }
        return (test[y][x] > 0 && test[y][x] <= 6 && this.solde.get() >= piege.getCout());
    }
    public boolean peuxPoserPiegeCoord(int x, int y){
        int[][] test = level.loadLevel(epoqueActuel.get());

        if (y < 0 || y >= test.length || x < 0 || x >= test[y].length) return false;

        for (int i = 0; i < defenses.size(); i++) {
            if (defenses.get(i).getX() == x && defenses.get(i).getY() == y) return false;
        }
        return (test[y][x] > 0 && test[y][x] <= 6 );
    }
    public boolean poserTour(Tour tour) {

        int caseX = (int) tour.getX();
        int caseY = (int) tour.getY();

        if (peuxPoserTour(this.epoqueActuel.get(), tour)) {
            depenserArgent(tour.getCout());
            addDefense(tour);
            System.out.println("Tour posée : " + caseX + ", " + caseY + " -" + tour.getCout());
            tour.inflation();
            return true;
        } else {
            System.out.println(" la case " + caseX + ", " + caseY + " est occupee ou invalide ou argent insuffisant");
            return false;
        }
    }
    public boolean peuxPoserTour(int epoque, Tour tour ) {
        int[][] grille = level.loadLevel(epoque);
        int caseX = (int) tour.getX();
        int caseY = (int) tour.getY();

        if (caseY < 0 || caseY >= grille.length || caseX < 0 || caseX >= grille[caseY].length) return false;
        if (grille[caseY][caseX] != 0) return false;

        for (Defense d : defenses) {
            if ((int)d.getX() == caseX && (int)d.getY() == caseY) return false;
        }
        return this.solde.get() >= tour.getCout();
    }

    // preview
    public boolean peuxPoserTourCoord( int caseX, int caseY) {
        int[][] grille = level.loadLevel(epoqueActuel.get());

        if (caseY < 0 || caseY >= grille.length || caseX < 0 || caseX >= grille[caseY].length) return false;
        if (grille[caseY][caseX] != 0) return false;

        for (Defense d : defenses) {
            if ((int)d.getX() == caseX && (int)d.getY() == caseY) return false;
        }
        return true;

    }

    public void activerUltime(){
        if(compteurKill.get() >= ultimeActuelle.getCompteurKill()){
            compteurKill.set(compteurKill.get() - ultimeActuelle.getCompteurKill());
            ultimeActuelle.activerUlt(ennemis);
        }
    }


    public void ajouterArgent(int somme) { this.solde.set(this.solde.get() + somme); }
    public void depenserArgent(int somme){ if(solde.get() >= somme) solde.set(solde.get() - somme); }
    public boolean perdu(){ return pvBase.get() <= 0; }
    public int getSolde() { return solde.get(); }
    public IntegerProperty getSoldeProperty(){ return solde; }
    public int getPvBase() { return pvBase.get(); }
    public IntegerProperty getPvBaseProperty(){ return pvBase; }
    public int getCompteurKill(){
        return compteurKill.get();
    }
    public IntegerProperty getCompteurKillProperty(){
        return compteurKill;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Preview getPreview() {
        return preview;
    }

    public void prochaineEpoque(){
        this.epoqueActuel.set(epoqueActuel.get() + 1);
    }

    public void changerEpoque(int epoque){
        if(epoque >= 0 && epoque < 5){
            this.epoqueActuel.set(epoque);
        }
    }


    public void setModeExtreme(boolean extreme) {
        this.modeExtreme = extreme;
    }
    public boolean isModeExtreme() {
        return this.modeExtreme;
    }
}
