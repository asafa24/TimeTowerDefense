package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.PluieMeteorites;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.TempeteDeSable;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.Ultime;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Boss;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.CharAssaut;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.Dictateur;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.SoldatRouge;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.contemporain.atk.ennemis.Vehicule;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis.AraigneeRobot;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis.Generateur;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis.IA;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.future.atk.ennemis.Robot;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Cavalier;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Chevalier;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Moine;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.ennemis.Roi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Compsognathus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Triceratops;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Tyrannosaurus;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.ennemis.Velociraptor;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.preview.Preview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jeu {

    private List<Epoque> epoques;
    private ObjectProperty<Epoque> epoque;
    // private IntegerProperty epoqueActuel;

    // private List<List<Point2D>> routes;

    private ObservableList<Ennemi> ennemis;
    private ObservableList<Defense> defenses;
    private ObservableList<Projectile> projectiles;
    private int frame;

    private Preview preview = new Preview(0.0, 0.0, this, -1);
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

        this.epoques = new ArrayList<>(List.of(
                new Prehistoire(), new Antiquite(), new MoyenAge(), new Contemporain(), new Futur()));

        // epoqueActuel à vocation à être remplacé par epoque
        this.epoque = new SimpleObjectProperty<>(epoques.get(0));
        // this.epoqueActuel = new SimpleIntegerProperty(0);

        this.ennemis = FXCollections.observableArrayList();
        this.defenses = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();

        this.solde = new SimpleIntegerProperty(200);
        this.pvBase = new SimpleIntegerProperty(50);
        this.frame = 0;
        // this.routes = new ArrayList<>();
        // newRoute();
        this.vague = new Vague();
        this.delay = 0;
        this.ultimeActuelle = new PluieMeteorites();
        this.compteurKill = new SimpleIntegerProperty(0);
        this.ultimes = new Ultime[] { new PluieMeteorites(), new TempeteDeSable(), new PluieMeteorites(),
                new PluieMeteorites(), new PluieMeteorites() };
    }

    // Méthode principale qui met à jour l'état du jeu à chaque "tick" de la boucle
    // de jeu.
    // Gère les actions des tours, le déplacement des projectiles, le comportement
    // des ennemis,
    // et le déclenchement des vagues.

    public boolean tick() {
        if (!perdu()) {
            ultimeActuelle.tick(ennemis);

            // Itération inversée pour éviter les problèmes de concurrence lors de la
            // suppression
            for (int i = defenses.size() - 1; i >= 0; i--) {
                Defense d = defenses.get(i);
                d.agir(ennemis, projectiles);
                if (d instanceof Destructible && ((Destructible) d).estMort()) {
                    defenses.remove(i);
                }
            }

            for (int i = projectiles.size() - 1; i >= 0; i--) {
                Projectile p = projectiles.get(i);
                if (p.aAtteintCible()) {
                    p.appliquerImpact(ennemis);
                    projectiles.remove(i);
                    continue;
                }
                p.deplacer();
            }

            for (int i = ennemis.size() - 1; i >= 0; i--) {
                Ennemi e = ennemis.get(i);
                if (e.estMort()) {
                    ajouterArgent(e.getRecompense());
                    this.compteurKill.set(this.compteurKill.get() + 1);

                    // Gère les ennemis qui apparaissent à la mort d'un autre (ex: Golem -> Golimes)
                    List<Ennemi> nouveauxEnnemis = e.onDeath();
                    if (!nouveauxEnnemis.isEmpty()) {
                        ennemis.addAll(nouveauxEnnemis);
                    }

                    ennemis.remove(i);
                    if (e instanceof Boss) {
                        vague.levelSuiv();
                        epoqueSuivante();
                    }
                    continue;
                }

                if (e.aAtteintLaBase()) {
                    ennemis.remove(i);
                    pvBase.set(pvBase.get() - e.getPv());
                    continue;
                }
                e.agir(ennemis, defenses);
                e.avancer();
            }

            // Gestion de l'apparition des ennemis et de la transition entre les vagues
            if (frame % delaySpawnMob == 0 && !vague.getQueue().isEmpty() && frame > TEMPS_ENTRE_VAGUE) {
                addEnnemi(creerEnnemi(vague.defiler()));
            } else {
                // Si la vague est terminée, lance un délai avant la prochaine
                if (delay > 600) {
                    delay = 0;
                    vague.vagueSuivante();
                    if (delaySpawnMob > 16)
                        delaySpawnMob -= 8; // Les vagues deviennent plus denses
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

    public void activerUltime() {
        if (compteurKill.get() >= ultimeActuelle.getCompteurKill()) {
            compteurKill.set(compteurKill.get() - ultimeActuelle.getCompteurKill());
            ultimeActuelle.activerUlt(ennemis);
        }
    }

    private Ennemi creerEnnemi(int id) {
        // Random rand = new Random();
        // List<Point2D> route = routes.get(rand.nextInt(routes.size()));
        List<Point2D> chemin = this.epoque.getValue().cheminDepartArrivee();

        // switch (this.epoqueActuel.get()) {
        switch (this.epoques.indexOf(this.epoque.getValue())) {

            case 0 -> {
                switch (id) {
                    case 0:
                        return new Compsognathus(chemin);
                    case 1:
                        return new Velociraptor(chemin);
                    case 2:
                        return new Triceratops(chemin);
                    default:
                        return new Tyrannosaurus(chemin);
                }
            }
            case 1 -> {
                switch (id) {
                    case 0:
                        return new Momie(chemin);
                    case 1:
                        return new Chacal(chemin);
                    case 2:
                        return new GolemSable(chemin);
                    default:
                        return new Sarko(chemin, this);
                }
            }
            case 2 -> {
                switch (id) {
                    case 0:
                        return new Moine(chemin);
                    case 1:
                        return new Chevalier(chemin);
                    case 2:
                        return new Cavalier(chemin);
                    default:
                        return new Roi(chemin);
                }
            }
            case 3 -> {
                switch (id) {
                    case 0:
                        return new SoldatRouge(chemin);
                    case 1:
                        return new Vehicule(chemin);
                    case 2:
                        return new CharAssaut(chemin);
                    default:
                        return new Dictateur(chemin);
                }
            }
            default -> {
                switch (id) {
                    case 0:
                        return new Robot(chemin);
                    case 1:
                        return new AraigneeRobot(chemin);
                    case 2:
                        return new Generateur(chemin);
                    default:
                        return new IA(chemin);
                }
            }
        }
    }

    // public void newRoute() {
    // this.routes = new ArrayList<>();
    // Point2D[] departs = ennemisDeparts(epoqueActuel.get());
    // Point2D arrivee = ennemiArrivee(epoqueActuel.get());
    // for (Point2D depart : departs) {
    // routes.add(level.calculerChemin(epoqueActuel.get(), depart, arrivee));
    // }
    // }

    // public Point2D[] ennemisDeparts(int epoque) {
    // switch (epoque) {
    // case 0:
    // return new Point2D[] { new Point2D(0, 9) };
    // case 1:
    // return new Point2D[] { new Point2D(10, 10) };
    // case 2:
    // return new Point2D[] { new Point2D(6, 10), new Point2D(0, 5), new Point2D(12,
    // 5) };
    // case 3:
    // return new Point2D[] { new Point2D(6, 10), new Point2D(6, 10), new Point2D(6,
    // 10), new Point2D(6, 10) };
    // case 4:
    // return new Point2D[] { new Point2D(2, 10), new Point2D(10, 10), new
    // Point2D(2, 10),
    // new Point2D(10, 10) };
    // }
    // return new Point2D[] { new Point2D(0, 0) };
    // }

    // public Point2D ennemiArrivee(int epoque) {
    // switch (epoque) {
    // case 0:
    // return new Point2D(10, 1);
    // case 1:
    // return new Point2D(0, 2);
    // default:
    // return new Point2D(6, 0);
    // }
    // }

    public void nuke() {
        ennemis.clear();
        defenses.clear();
        projectiles.clear();
    }

    public void resetTimersEtVague() {
        this.frame = 0;
        this.delay = 0;
        this.delaySpawnMob = 80;
        // this.vague.reinitialiser(this.epoqueActuel.get());
        this.vague.reinitialiser(this.epoques.indexOf(this.epoque.getValue()));

    }

    public void epoqueSuivante() {
        // TODO
        // this.epoqueActuel.set(epoqueActuel.get() + 1);

        int indiceEpoqueSuivante = Math.min(
                this.epoques.indexOf(this.epoque.getValue()) + 1,
                this.epoques.size() - 1);
        this.epoque.set(this.epoques.get(indiceEpoqueSuivante));

        actualiserUltime();
    }

    public void epoquePrecedente() {
        // TODO

        int indiceEpoqueSuivante = Math.max(
                this.epoques.indexOf(this.epoque.getValue()) - 1,
                0);
        this.epoque.set(this.epoques.get(indiceEpoqueSuivante));

        actualiserUltime();
    }

    public void changerEpoque(int epoque) {
        // TODO methode pas utilisée
        if (epoque >= 0 && epoque < 5) {
            // this.epoqueActuel.set(epoque);
        }
    }

    public void actualiserUltime() {
        // this.ultimeActuelle = ultimes[this.epoqueActuel.get()];
        this.ultimeActuelle = ultimes[this.epoques.indexOf(this.epoque.getValue())];

    }

    public boolean perdu() {
        return pvBase.get() <= 0;
    }

    public void ajouterArgent(int somme) {
        this.solde.set(this.solde.get() + somme);
    }

    public void depenserArgent(int somme) {
        if (solde.get() >= somme)
            solde.set(solde.get() - somme);
    }

    public boolean poserTour(Tour tour) {
        int caseX = (int) tour.getX();
        int caseY = (int) tour.getY();

        if (peuxPoserTour(this.epoques.indexOf(this.epoque.getValue()), tour)) {
            depenserArgent(tour.getCout());
            addDefense(tour);
            tour.inflation();
            return true;
        }
        return false;
    }

    public boolean poserPiege(Piege piege) {
        int caseX = (int) (piege.getX());
        int caseY = (int) (piege.getY());

        if (peuxPoserPiege(this.epoques.indexOf(this.epoque.getValue()), piege)) {
            depenserArgent(piege.getCout());
            addDefense(piege);
            return true;
        }
        return false;
    }

    public boolean peuxPoserTour(int epoque, Tour tour) {
        // int[][] grille = level.loadLevel(epoque);
        int x = (int) tour.getX();
        int y = (int) tour.getY();

        // if (caseY < 0 || caseY >= grille.length || caseX < 0 || caseX >=
        // grille[caseY].length)
        // return false;
        if (!this.epoque.getValue().coordonneeValide(x, y)) {
            return false;
        }
        if (this.epoque.getValue().valeurCoordonnee(x, y) != 0)
            return false;

        for (Defense d : defenses) {
            if ((int) d.getX() == x && (int) d.getY() == y)
                return false;
        }
        return this.solde.get() >= tour.getCout();
    }

    public boolean peuxPoserPiege(int epoque, Piege piege) {
        // int[][] test = level.loadLevel(epoque);

        int x = (int) piege.getX();
        int y = (int) piege.getY();

        // if (y < 0 || y >= test.length || x < 0 || x >= test[y].length)
        // return false;
        if (!this.epoque.getValue().coordonneeValide(x, y)) {
            return false;
        }

        // TODO Liste de defenses, ennemis dans Epoque
        // a remplacer par un appel à "positionOccupee..."
        for (int i = 0; i < defenses.size(); i++) {
            if (defenses.get(i).getX() == x && defenses.get(i).getY() == y)
                return false;
        }
        // return (test[y][x] > 0 && test[y][x] <= 9 && this.solde.get() >=
        // piege.getCout());
        return this.epoque.getValue().valeurCoordonnee(x, y) > 0
                && this.epoque.getValue().valeurCoordonnee(x, y) <= 9
                && this.solde.get() >= piege.getCout();
    }

    public void preview(double x, double y) {
        preview.update(x, y, id);
    }

    public boolean peuxPoserTourCoord(int caseX, int caseY) {
        // int[][] grille = level.loadLevel(epoqueActuel.get());

        // if (caseY < 0 || caseY >= grille.length || caseX < 0 || caseX >=
        // grille[caseY].length)
        // return false;
        if (!this.epoque.getValue().coordonneeValide(caseX, caseY)) {
            return false;
        }
        // if (grille[caseY][caseX] != 0)
        // return false;
        if (this.epoque.getValue().valeurCoordonnee(caseX, caseY) != 0)
            return false;

        for (Defense d : defenses) {
            if ((int) d.getX() == caseX && (int) d.getY() == caseY)
                return false;
        }
        return true;
    }

    public boolean peuxPoserPiegeCoord(int x, int y) {
        // int[][] test = level.loadLevel(epoqueActuel.get());

        // if (y < 0 || y >= test.length || x < 0 || x >= test[y].length)
        // return false;
        if (!this.epoque.getValue().coordonneeValide(x, y)) {
            return false;
        }

        for (int i = 0; i < defenses.size(); i++) {
            if (defenses.get(i).getX() == x && defenses.get(i).getY() == y)
                return false;
        }
        // return (test[y][x] > 0 && test[y][x] <= 9);
        return (this.epoque.getValue().valeurCoordonnee(x, y) > 0
                && this.epoque.getValue().valeurCoordonnee(x, y) <= 9);
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

    public ObjectProperty<Epoque> getEpoqueProperty() {
        return epoque;
    }

    public int getEpoqueActuel() {
        // TODO à supprimer ?
        // return epoqueActuel.get();
        return this.epoques.indexOf(this.epoque.getValue());
    }

    // public IntegerProperty getEpoqueActuelProperty() {
    // return epoqueActuel;
    // }

    public void setEpoqueActuel(int epoqueActuel) {
        this.epoque.setValue(this.epoques.get(epoqueActuel));
    }

    public ObservableList<Ennemi> getEnnemi() {
        return ennemis;
    }

    public ObservableList<Defense> getDefenses() {
        return defenses;
    }

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }

    public int getSolde() {
        return solde.get();
    }

    public IntegerProperty getSoldeProperty() {
        return solde;
    }

    public int getPvBase() {
        return pvBase.get();
    }

    public IntegerProperty getPvBaseProperty() {
        return pvBase;
    }

    public int getCompteurKill() {
        return compteurKill.get();
    }

    public IntegerProperty getCompteurKillProperty() {
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