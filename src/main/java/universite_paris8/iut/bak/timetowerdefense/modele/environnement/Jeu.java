package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.PluieMeteorites;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.Ultime;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.Compsognathus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.Triceratops;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.Velociraptor;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.MiniVolcan;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.ArbreRuste;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.CatapulteOs;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.LanceFilet;
import universite_paris8.iut.bak.timetowerdefense.modele.preview.Preview;

import java.util.List;

public class Jeu {
    private Defense temoinTourUn;
    private ArbreRuste temoinTourDeux;
    private Defense temoinTourTrois;
    private Defense temoinTourQuatre;
    private ObservableList<Ennemi> ennemis;
    private ObservableList<Defense> defenses;
    private ObservableList<Projectile> projectiles;
    private int frame;
    private List<Point2D> route ;

    private Level level;
    private int epoqueActuel;
    private Preview preview = new  Preview(0.0,0.0,this,-1);

    private IntegerProperty solde;
    private IntegerProperty pvBase;

    private int typeTourSelectionne ;

    private Vague vague;
    private int delay;
    private final int TEMPS_ENTRE_VAGUE = 220 ;
    private int delaySpawnMob = 80;
    private int id = -1;

    private Ultime ultimeActuelle;
    private IntegerProperty compteurKill;
    private IntegerProperty[] prixBoutique = new IntegerProperty[] {
            new SimpleIntegerProperty(50),
            new SimpleIntegerProperty(150),
            new SimpleIntegerProperty(150),
            new SimpleIntegerProperty(25)
    };


    public Jeu() {
        this.ennemis = FXCollections.observableArrayList();
        this.defenses = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        this.level = new Level();
        this.solde = new SimpleIntegerProperty(2000);
        this.pvBase = new SimpleIntegerProperty(50);
        this.epoqueActuel = 0;
        this.frame = 0;
        typeTourSelectionne = 0 ;
        this.route = level.calculerChemin(0, new Point2D(0, 9), new Point2D(10, 1));
        this.vague = new Vague();
        this.delay = 0;
        this.ultimeActuelle = new PluieMeteorites();
        this.compteurKill = new SimpleIntegerProperty(0);
        this.chargerEpoque(0);

        this.temoinTourUn = new MiniVolcan(0, 0);
        this.temoinTourDeux = new ArbreRuste(0, 0);
        this.temoinTourTrois = new CatapulteOs(0, 0);
        this.temoinTourQuatre = new LanceFilet(0, 0);

    }
    public void chargerEpoque(int epoque) {
        this.epoqueActuel = epoque;
        switch (epoque) {
            case 0: // Préhistoire
                prixBoutique[0].set(25);
                prixBoutique[1].set(50);
                prixBoutique[2].set(125);
                prixBoutique[3].set(150);
                break;
            case 1: // Moyen-Âge
                prixBoutique[0].set(100);
                prixBoutique[1].set(220);
                prixBoutique[2].set(300);
                prixBoutique[3].set(60);
                break;
        }
    }
    public IntegerProperty getPrixSlotProperty(int indexSlot) {
        return prixBoutique[indexSlot];
    }

    public int getEpoqueActuel() {
        return epoqueActuel;
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
            ultimeActuelle.tick(ennemis);
            for (int i = defenses.size() - 1; i >= 0; i--) {
                Defense d = defenses.get(i);
                d.agir(ennemis, projectiles);
                if(d instanceof Destructible && ((Destructible) d).estMort()){
                    defenses.remove(i);
                }
            }

            if (!projectiles.isEmpty()) {
                for (int i = projectiles.size() - 1; i >= 0; i--) {
                    Projectile p = projectiles.get(i);
                    if(p.aAtteintCible()){
                        p.appliquerImpact(ennemis);
                        projectiles.remove(i);
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

        }
        if (frame % delaySpawnMob == 0 && !vague.getQueue().isEmpty() && frame > TEMPS_ENTRE_VAGUE) {
            addEnnemi(creerEnnemi(vague.defiler() ));
        }
        else{
            if (delay > 600 ){
                delay = 0;
                vague.vagueSuivante();
                if (delaySpawnMob > 16) delaySpawnMob -= 8;
            }
            else {
                if (vague.getQueue().isEmpty()){
                    delay++;
                }

            }
        }
        frame++;
    }
    public void preview(double x, double y) {preview.update(x,y,id);
    }

    public void poserTour(Tour tour) {
        int caseX = (int) tour.getX();
        int caseY = (int) tour.getY();

        if (peuxPoserTour(this.epoqueActuel, tour)) {
            depenserArgent(tour.getCout());
            addDefense(tour);
            System.out.println("Tour posée : " + caseX + ", " + caseY + " -" + tour.getCout());
            tour.inflation();
        } else {
            System.out.println(" la case " + caseX + ", " + caseY + " est occupee ou invalide ou argent insuffisant");
        }
    }

    public void poserPiege(Piege piege ){
        int caseX = (int) (piege.getX()) ;
        int caseY = (int) (piege.getY())  ;

        if (peuxPoserPiege(this.epoqueActuel, piege)) {
            depenserArgent(piege.getCout());
            addDefense(piege);
            System.out.println("Piège posé : " + caseX + ", " + caseY + " -" + piege.getCout());
        } else {
            System.out.println(" la case " + caseX + ", " + caseY + " est occupee ou invalide ou argent insuffisant");
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
    public boolean peuxPoserTourCoord(int epoque, int caseX, int caseY) {
        int[][] grille = level.loadLevel(epoque);

        if (caseY < 0 || caseY >= grille.length || caseX < 0 || caseX >= grille[caseY].length) return false;
        if (grille[caseY][caseX] != 0) return false;

        for (Defense d : defenses) {
            if ((int)d.getX() == caseX && (int)d.getY() == caseY) return false;
        }
        return true;

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

    public boolean peuxPoserPiegeCoord(int epoque, int x, int y){
        int[][] test = level.loadLevel(epoque);

        if (y < 0 || y >= test.length || x < 0 || x >= test[y].length) return false;

        for (int i = 0; i < defenses.size(); i++) {
            if (defenses.get(i).getX() == x && defenses.get(i).getY() == y) return false;
        }
        return (test[y][x] > 0 && test[y][x] <= 6 );
    }

    public boolean piegeloc(int x , int y){
        for (int i = 0; i < defenses.size(); i++) {
            if (defenses.get(i).getX() == x && defenses.get(i).getY() == y) return false;
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

    private Ennemi creerEnnemi(int id ) {
        switch (id) {
            case 0 : return new Compsognathus(route);
            case 1 : return new Velociraptor(route);
            case 2 : return new Triceratops(route);
            default : return new Tyrannosaurus(0, 64 * 9, 1000, 1, 400, 900, route);
        }
    }
    public IntegerProperty getCoutTourUnProperty(){
        return this.temoinTourUn.getCoutIntegerProperty();
    }
    public IntegerProperty getCoutTourDeuxProperty(){
        return ArbreRuste.coutPropertyArbre();
    }
    public IntegerProperty getCoutTourTroisProperty(){
        return this.temoinTourTrois.getCoutIntegerProperty();
    }
    public IntegerProperty getCoutTourQuatreProperty(){
        return this.temoinTourQuatre.getCoutIntegerProperty();
    }

}
