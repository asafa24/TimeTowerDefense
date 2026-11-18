package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import java.util.List;
import java.util.function.Consumer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.Ultime;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

public class EpoqueDeJeu implements Epoque, AffecteEnnemi, AffecteDefense {

    private List<Epoque> epoques;
    private ObjectProperty<Epoque> courante;
    private ObservableList<Ennemi> ennemis = FXCollections.observableArrayList();
    private ObservableList<Defense> defenses = FXCollections.observableArrayList();
    private ObservableList<Projectile> projectiles = FXCollections.observableArrayList();

    public EpoqueDeJeu(Epoque... epoques) {
        this.epoques = List.of(epoques);
        this.courante = new SimpleObjectProperty<>(this.epoques.get(0));
    }

    public ObjectProperty<Epoque> courantProperty() {
        return courante;
    }

    public int indiceCourant() {
        return epoques.indexOf(courante.get());
    }

    public int nombreEpoques() {
        return epoques.size();
    }

    public void setIndice(int i) {
        if (i == indiceCourant() || i < 0 || i >= nombreEpoques()) {
            return;
        }
        nuke();
        courante.set(epoques.get(i));
    }

    public void suivante() {
        setIndice(Math.min(indiceCourant() + 1, epoques.size() - 1));
    }

    public void precedente() {
        setIndice(Math.max(indiceCourant() - 1, 0));
    }

    @Override
    public boolean coordonneeValide(int x, int y) {
        return courante.get().coordonneeValide(x, y);
    }

    @Override
    public int valeurCoordonnee(int x, int y) {
        return courante.get().valeurCoordonnee(x, y);
    }

    @Override
    public int hauteurMap() {
        return courante.get().hauteurMap();
    }

    @Override
    public int longueurMap() {
        return courante.get().longueurMap();
    }

    @Override
    public List<Point2D> cheminDepartArrivee() {
        return courante.get().cheminDepartArrivee();
    }

    @Override
    public Vague getVague() {
        return courante.get().getVague();
    }

    @Override
    public Ultime getUltime() {
        return courante.get().getUltime();
    }

    @Override
    public Ennemi ennemi1() {
        return courante.get().ennemi1();
    }

    @Override
    public Ennemi ennemi2() {
        return courante.get().ennemi2();
    }

    @Override
    public Ennemi ennemi3() {
        return courante.get().ennemi3();
    }

    @Override
    public Ennemi ennemi4() {
        return courante.get().ennemi4();
    }

    @Override
    public String toString() {
        return courante.get().toString();
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

    @Override
    public void addEnnemi(Ennemi e) {
        ennemis.add(e);
    }

    public void addDefense(Defense d) {
        defenses.add(d);
    }

    @Override
    public void affecterEnnemis(Consumer<Ennemi> c) {
        for (Ennemi e : ennemis)
            c.accept(e);
    }

    @Override
    public void affecterDefenses(Consumer<Defense> c) {
        for (Defense d : defenses)
            c.accept(d);
    }

    public void creerEnnemi(int type) {
        addEnnemi(switch (type) {
            case 0 -> ennemi1();
            case 1 -> ennemi2();
            case 2 -> ennemi3();
            default -> ennemi4();
        });
    }

    public void nuke() {
        ennemis.clear();
        defenses.clear();
        projectiles.clear();
    }

    public void actionUltime() {
        getUltime().agir(this);
    }

    public boolean peutPoserTour(int x, int y) {
        return valeurCoordonnee(x, y) == 0 && !defenseEn(x, y);
    }

    public boolean peutPoserPiege(int x, int y) {
        int v = valeurCoordonnee(x, y);
        return v > 0 && v <= 9 && !defenseEn(x, y);
    }

    private boolean defenseEn(int x, int y) {
        for (Defense d : defenses)
            if ((int) d.getX() == x && (int) d.getY() == y)
                return true;
        return false;
    }
}
