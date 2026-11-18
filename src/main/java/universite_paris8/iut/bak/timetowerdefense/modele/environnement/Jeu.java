package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Boss;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.preview.Preview;
import java.util.List;

public class Jeu {

    private final EpoqueDeJeu epoque;

    private int frame;

    private Preview preview = new Preview(0.0, 0.0, this, -1);
    private IntegerProperty solde;
    private IntegerProperty pvBase;

    private int delay;
    private final int TEMPS_ENTRE_VAGUE = 220;
    private int delaySpawnMob = 80;
    private int id = -1;

    private IntegerProperty compteurKill;

    private boolean modeExtreme = false;

    public Jeu() {
        this.epoque = new EpoqueDeJeu(
                new Prehistoire(), new Antiquite(), new MoyenAge(), new Contemporain(), new Futur());

        this.solde = new SimpleIntegerProperty(200);
        this.pvBase = new SimpleIntegerProperty(50);
        this.frame = 0;
        this.delay = 0;
        this.compteurKill = new SimpleIntegerProperty(0);
    }

    public ObjectProperty<Epoque> getEpoqueProperty() {
        return this.epoque.courantProperty();
    }

    public int getEpoqueActuel() {
        return this.epoque.indiceCourant();
    }

    public void setEpoqueActuel(int epoqueActuel) {
        this.epoque.setIndice(epoqueActuel);
    }

    public int getNombreEpoques() {
        return this.epoque.nombreEpoques();
    }

    public ObservableList<Ennemi> getEnnemi() {
        return epoque.getEnnemi();
    }

    public ObservableList<Defense> getDefenses() {
        return this.epoque.getDefenses();
    }

    public ObservableList<Projectile> getProjectiles() {
        return this.epoque.getProjectiles();
    }

    public Vague getVague() {
        return this.epoque.getVague();
    }

    public void nuke() {
        this.epoque.nuke();
    }

    public void epoqueSuivante() {
        this.epoque.suivante();
    }

    public void epoquePrecedente() {
        this.epoque.precedente();
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

    // Méthode principale qui met à jour l'état du jeu à chaque "tick" de la boucle
    // de jeu.
    // Gère les actions des tours, le déplacement des projectiles, le comportement
    // des ennemis,
    // et le déclenchement des vagues.

    public boolean tick() {
        if (perdu()) {
            return false;
        }

        epoque.actionUltime();
        mettreAJourDefenses();
        mettreAJourProjectiles();
        if (mettreAJourEnnemis()) {
            return true;
        }
        gererApparitionEtTransitionVague();
        frame++;
        return true;
    }

    private void mettreAJourDefenses() {
        for (int i = epoque.getDefenses().size() - 1; i >= 0; i--) {
            Defense defense = epoque.getDefenses().get(i);
            defense.agir(epoque.getEnnemi(), epoque.getProjectiles());
            if (defense instanceof Destructible && ((Destructible) defense).estMort()) {
                epoque.getDefenses().remove(i);
            }
        }
    }

    private void mettreAJourProjectiles() {
        for (int i = epoque.getProjectiles().size() - 1; i >= 0; i--) {
            Projectile projectile = epoque.getProjectiles().get(i);
            if (projectile.aAtteintCible()) {
                projectile.appliquerImpact(epoque.getEnnemi());
                epoque.getProjectiles().remove(i);
                continue;
            }
            projectile.deplacer();
        }
    }

    /** @return true si un boss a fait avancer le jeu à l'époque suivante. */
    private boolean mettreAJourEnnemis() {
        for (int i = epoque.getEnnemi().size() - 1; i >= 0; i--) {
            Ennemi ennemi = epoque.getEnnemi().get(i);
            if (ennemi.estMort()) {
                traiterMortEnnemi(ennemi);
                epoque.getEnnemi().remove(i);
                if (ennemi instanceof Boss) {
                    epoqueSuivante();
                    return true;
                }
                continue;
            }

            if (ennemi.aAtteintLaBase()) {
                epoque.getEnnemi().remove(i);
                pvBase.set(pvBase.get() - ennemi.getPv());
                continue;
            }
            ennemi.agir(epoque, epoque);
            ennemi.avancer();
        }
        return false;
    }

    private void traiterMortEnnemi(Ennemi ennemi) {
        ajouterArgent(ennemi.getRecompense());
        compteurKill.set(compteurKill.get() + 1);

        List<Ennemi> nouveauxEnnemis = ennemi.onDeath();
        if (!nouveauxEnnemis.isEmpty()) {
            epoque.getEnnemi().addAll(nouveauxEnnemis);
        }
    }

    private void gererApparitionEtTransitionVague() {
        if (frame % delaySpawnMob == 0
                && !epoque.getVague().getQueue().isEmpty()
                && frame > TEMPS_ENTRE_VAGUE) {
            epoque.creerEnnemi(epoque.getVague().defiler());
        } else if (delay > 600) {
            delay = 0;
            epoque.getVague().vagueSuivante();
            if (delaySpawnMob > 16) {
                delaySpawnMob -= 8;
            }
        } else if (epoque.getVague().getQueue().isEmpty()) {
            delay++;
        }
    }

    public void activerUltime() {
        if (compteurKill.get() >= epoque.getUltime().getCompteurKill()) {
            compteurKill.set(compteurKill.get() - epoque.getUltime().getCompteurKill());
            epoque.getUltime().activerUlt(epoque);
        }
    }

    public void resetTimersEtVague() {
        this.frame = 0;
        this.delay = 0;
        this.delaySpawnMob = 80;
        this.epoque.getVague().reinitialiser();

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

    public void preview(double x, double y) {
        preview.update(x, y, id);
    }

    public boolean peuxPoserTour(Tour tour) {
        return epoque.peutPoserTour((int) tour.getX(), (int) tour.getY())
                && solde.get() >= tour.getCout();
    }

    public boolean peuxPoserPiege(Piege piege) {
        return epoque.peutPoserPiege((int) piege.getX(), (int) piege.getY())
                && solde.get() >= piege.getCout();
    }

    public boolean peuxPoserTourCoord(int x, int y) {
        return epoque.peutPoserTour(x, y);
    }

    public boolean peuxPoserPiegeCoord(int x, int y) {
        return epoque.peutPoserPiege(x, y);
    }

    public boolean poserTour(Tour tour) {
        if (peuxPoserTour(tour)) {
            depenserArgent(tour.getCout());
            epoque.addDefense(tour);
            tour.inflation();
            return true;
        }
        return false;
    }

    public boolean poserPiege(Piege piege) {
        if (peuxPoserPiege(piege)) {
            depenserArgent(piege.getCout());
            epoque.addDefense(piege);
            return true;
        }
        return false;
    }

}
