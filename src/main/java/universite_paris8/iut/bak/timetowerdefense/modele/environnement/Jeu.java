package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.PluieMeteorites;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.TempeteDeSable;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.Ultime;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Boss;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques.FabriqueEnnemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques.FabriqueEnnemiAntiquite;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques.FabriqueEnnemiContemporain;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques.FabriqueEnnemiFutur;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques.FabriqueEnnemiMoyenAge;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.fabriques.FabriqueEnnemiPrehistoire;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.preview.Preview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jeu {

    private ObservableList<Ennemi> ennemis;
    private ObservableList<Defense> defenses;
    private ObservableList<Projectile> projectiles;
    private int frame;
    private List<List<Point2D>> routes;
    private final List<FabriqueEnnemi> fabriquesEnnemis;

    private final Level level;
    private IntegerProperty epoqueActuel;
    private Preview preview = new  Preview(0.0,0.0,this,-1);

    private IntegerProperty solde;
    private IntegerProperty pvBase;
    private Vague vague;
    private int delay;
    private final int TEMPS_ENTRE_VAGUE = 220;
    private int delaySpawnMob = 80;
    private int id = -1;

    private Ultime ultimeActuelle;
    private IntegerProperty compteurKill;
    private Ultime[] ultimes;

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
        this.routes = new ArrayList<>();

        this.fabriquesEnnemis = List.of(
                new FabriqueEnnemiPrehistoire(),
                new FabriqueEnnemiAntiquite(),
                new FabriqueEnnemiMoyenAge(),
                new FabriqueEnnemiContemporain(),
                new FabriqueEnnemiFutur());

        newRoute();
        this.vague = new Vague();
        this.delay = 0;
        this.ultimeActuelle = new PluieMeteorites();
        this.compteurKill = new SimpleIntegerProperty(0);
        this.ultimes = new Ultime[]{new PluieMeteorites(), new TempeteDeSable(), new PluieMeteorites(), new PluieMeteorites(), new PluieMeteorites()};
    }

//      Méthode principale qui met à jour l'état du jeu à chaque "tick" de la boucle de jeu.
//      Gère les actions des tours, le déplacement des projectiles, le comportement des ennemis,
//      et le déclenchement des vagues.

    public boolean tick() {
        if (perdu()) {
            return false;
        }

        ultimeActuelle.tick(ennemis);
        mettreAJourDefenses();
        mettreAJourProjectiles();
        mettreAJourEnnemis();
        gererApparitionEtTransitionVague();
        frame++;
        return true;
    }

    private void mettreAJourDefenses() {
        for (int i = defenses.size() - 1; i >= 0; i--) {
            Defense defense = defenses.get(i);
            defense.agir(ennemis, projectiles);
            if (defense instanceof Destructible && ((Destructible) defense).estMort()) {
                defenses.remove(i);
            }
        }
    }

    private void mettreAJourProjectiles() {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile projectile = projectiles.get(i);
            if (projectile.aAtteintCible()) {
                projectile.appliquerImpact(ennemis);
                projectiles.remove(i);
                continue;
            }
            projectile.deplacer();
        }
    }

    private void mettreAJourEnnemis() {
        for (int i = ennemis.size() - 1; i >= 0; i--) {
            Ennemi ennemi = ennemis.get(i);
            if (ennemi.estMort()) {
                ajouterArgent(ennemi.getRecompense());
                compteurKill.set(compteurKill.get() + 1);

                // Certains ennemis font apparaître d'autres ennemis à leur mort
                List<Ennemi> nouveauxEnnemis = ennemi.onDeath();
                if (!nouveauxEnnemis.isEmpty()) {
                    ennemis.addAll(nouveauxEnnemis);
                }

                ennemis.remove(i);
                if (ennemi instanceof Boss) {
                    vague.levelSuiv();
                    prochaineEpoque();
                }
                continue;
            }

            if (ennemi.aAtteintLaBase()) {
                ennemis.remove(i);
                pvBase.set(pvBase.get() - ennemi.getPv());
                continue;
            }
            ennemi.agir(ennemis, defenses);
            ennemi.avancer();
        }
    }

    private void gererApparitionEtTransitionVague() {
        if (frame % delaySpawnMob == 0 && !vague.getQueue().isEmpty() && frame > TEMPS_ENTRE_VAGUE) {
            addEnnemi(creerEnnemi(vague.defiler()));
        } else if (delay > 600) {
            delay = 0;
            vague.vagueSuivante();
            if (delaySpawnMob > 16) {
                delaySpawnMob -= 8;
            }
        } else if (vague.getQueue().isEmpty()) {
            delay++;
        }
    }

    public void activerUltime(){
        if(compteurKill.get() >= ultimeActuelle.getCompteurKill()){
            compteurKill.set(compteurKill.get() - ultimeActuelle.getCompteurKill());
            ultimeActuelle.activerUlt(ennemis);
        }
    }

    private Ennemi creerEnnemi(int id) {
        Random rand = new Random();
        List<Point2D> route = routes.get(rand.nextInt(routes.size()));
        int epoque = epoqueActuel.get();
        int indexFabrique = epoque >= 0 && epoque < fabriquesEnnemis.size()
                ? epoque
                : 0;
        return fabriquesEnnemis.get(indexFabrique).creerEnnemi(id, route, this);
    }

    public void newRoute() {
        this.routes = new ArrayList<>();
        Point2D[] departs = ennemisDeparts(epoqueActuel.get());
        Point2D arrivee = ennemiArrivee(epoqueActuel.get());
        for (Point2D depart : departs){
            routes.add(level.calculerChemin(epoqueActuel.get(), depart, arrivee));
        }
    }

    public Point2D[] ennemisDeparts(int epoque){
        switch(epoque){
            case 0: return new Point2D[]{new Point2D(0, 9)};
            case 1: return new Point2D[]{new Point2D(10, 10)};
            case 2: return new Point2D[]{new Point2D(6, 10), new Point2D(0, 5), new Point2D(12, 5)};
            case 3: return new Point2D[]{new Point2D(6, 10), new Point2D(6, 10),new Point2D(6, 10) ,new Point2D(6, 10)};
            case 4: return new Point2D[]{new Point2D(2, 10), new Point2D(10, 10), new Point2D(2, 10), new Point2D(10, 10)};
        }
        return new Point2D[]{new Point2D(0, 0)};
    }

    public Point2D ennemiArrivee(int epoque){
        switch (epoque){
            case 0: return new Point2D(10, 1);
            case 1: return new Point2D(0, 2);
            default: return new Point2D(6, 0);
        }
    }

    public void nuke(){
        ennemis.clear();
        defenses.clear();
        projectiles.clear();
    }

    public void resetTimersEtVague() {
        this.frame = 0;
        this.delay = 0;
        this.delaySpawnMob = 80;
        this.vague.reinitialiser(this.epoqueActuel.get());
    }

    public void prochaineEpoque(){
        this.epoqueActuel.set(epoqueActuel.get() + 1);
        actualiserUltime();
    }

    public void changerEpoque(int epoque){
        if(epoque >= 0 && epoque < 5){
            this.epoqueActuel.set(epoque);
        }
    }

    public void actualiserUltime(){
        this.ultimeActuelle = ultimes[this.epoqueActuel.get()];
    }

    public boolean perdu(){ return pvBase.get() <= 0; }

    public void ajouterArgent(int somme) { this.solde.set(this.solde.get() + somme); }
    public void depenserArgent(int somme){ if(solde.get() >= somme) solde.set(solde.get() - somme); }

    public boolean poserTour(Tour tour) {
        int caseX = (int) tour.getX();
        int caseY = (int) tour.getY();

        if (peuxPoserTour(this.epoqueActuel.get(), tour)) {
            depenserArgent(tour.getCout());
            addDefense(tour);
            tour.inflation();
            return true;
        }
        return false;
    }

    public boolean poserPiege(Piege piege ){
        int caseX = (int) (piege.getX()) ;
        int caseY = (int) (piege.getY())  ;

        if (peuxPoserPiege(this.epoqueActuel.get(), piege)) {
            depenserArgent(piege.getCout());
            addDefense(piege);
            return true;
        }
        return false;
    }

    public boolean peuxPoserTour(int epoque, Tour tour) {
        int[][] grille = level.loadLevel(epoque);
        int caseX = (int) tour.getX();
        int caseY = (int) tour.getY();
        return caseValidePourTour(grille, caseX, caseY)
                && solde.get() >= tour.getCout();
    }

    public boolean peuxPoserPiege(int epoque, Piege piege) {
        int[][] grille = level.loadLevel(epoque);
        int x = (int) piege.getX();
        int y = (int) piege.getY();
        return caseValidePourPiege(grille, x, y)
                && solde.get() >= piege.getCout();
    }

    public void preview(double x, double y) {
        preview.update(x, y, id);
    }

    public boolean peuxPoserTourCoord(int caseX, int caseY) {
        int[][] grille = level.loadLevel(epoqueActuel.get());
        return caseValidePourTour(grille, caseX, caseY);
    }

    public boolean peuxPoserPiegeCoord(int x, int y) {
        int[][] grille = level.loadLevel(epoqueActuel.get());
        return caseValidePourPiege(grille, x, y);
    }

    private boolean coordonneesDansGrille(int[][] grille, int x, int y) {
        return y >= 0 && y < grille.length && x >= 0 && x < grille[y].length;
    }

    private boolean caseValidePourTour(int[][] grille, int x, int y) {
        if (!coordonneesDansGrille(grille, x, y) || grille[y][x] != 0) {
            return false;
        }
        for (Defense defense : defenses) {
            if ((int) defense.getX() == x && (int) defense.getY() == y) {
                return false;
            }
        }
        return true;
    }

    private boolean caseValidePourPiege(int[][] grille, int x, int y) {
        if (!coordonneesDansGrille(grille, x, y)) {
            return false;
        }
        for (Defense defense : defenses) {
            if (defense.getX() == x && defense.getY() == y) {
                return false;
            }
        }
        // La valeur 7 représente la base dans les cartes : un piège ne peut pas y être posé.
        return grille[y][x] > 0 && grille[y][x] <= 9 && grille[y][x] != 7;
    }

    public void addEnnemi(Ennemi ennemi) {
        ennemis.add(ennemi);
    }

    public void addDefense(Defense defense) {
        defenses.add(defense);
    }

    public Vague getVague() {
        return vague;
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

    public ObservableList<Ennemi> getEnnemi() {
        return ennemis ;
    }

    public ObservableList<Defense> getDefenses() {
        return defenses;
    }

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public Preview getPreview() {
        return preview;
    }

    public void setModeExtreme(boolean extreme) {
        this.modeExtreme = extreme;
    }

    public boolean isModeExtreme() {
        return this.modeExtreme;
    }
}
