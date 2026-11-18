package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.*;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Boss;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;
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

        if (!perdu()) {

            epoque.actionUltime();

            // Itération inversée pour éviter les problèmes de concurrence lors de la
            // suppression
            for (int i = epoque.getDefenses().size() - 1; i >= 0; i--) {
                Defense d = epoque.getDefenses().get(i);
                d.agir(epoque.getEnnemi(),
                        epoque.getProjectiles());
                if (d instanceof Destructible && ((Destructible) d).estMort()) {
                    epoque.getDefenses().remove(i);
                }
            }

            for (int i = epoque.getProjectiles().size() - 1; i >= 0; i--) {
                Projectile p = epoque.getProjectiles().get(i);
                if (p.aAtteintCible()) {
                    p.appliquerImpact(epoque.getEnnemi());
                    epoque.getProjectiles().remove(i);
                    continue;
                }
                p.deplacer();
            }

            for (int i = epoque.getEnnemi().size() - 1; i >= 0; i--) {
                Ennemi e = epoque.getEnnemi().get(i);
                if (e.estMort()) {
                    ajouterArgent(e.getRecompense());
                    this.compteurKill.set(this.compteurKill.get() + 1);

                    // Gère les ennemis qui apparaissent à la mort d'un autre (ex: Golem -> Golimes)
                    List<Ennemi> nouveauxEnnemis = e.onDeath();
                    if (!nouveauxEnnemis.isEmpty()) {
                        epoque.getEnnemi().addAll(nouveauxEnnemis);
                    }

                    epoque.getEnnemi().remove(i);
                    if (e instanceof Boss) {
                        epoqueSuivante();
                        return true;
                    }
                    continue;
                }

                if (e.aAtteintLaBase()) {
                    epoque.getEnnemi().remove(i);
                    pvBase.set(pvBase.get() - e.getPv());
                    continue;
                }
                e.agir(epoque, epoque);
                e.avancer();
            }

            // Gestion de l'apparition des ennemis et de la transition entre les vagues
            if (frame % delaySpawnMob == 0
                    && !epoque.getVague().getQueue().isEmpty()
                    && frame > TEMPS_ENTRE_VAGUE) {
                epoque.creerEnnemi(epoque.getVague().defiler());
            } else {
                // Si la vague est terminée, lance un délai avant la prochaine
                if (delay > 600) {
                    delay = 0;
                    epoque.getVague().vagueSuivante();
                    if (delaySpawnMob > 16)
                        delaySpawnMob -= 8; // Les vagues deviennent plus denses
                } else {
                    if (epoque.getVague().getQueue().isEmpty()) {
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