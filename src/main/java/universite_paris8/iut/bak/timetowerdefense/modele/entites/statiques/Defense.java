package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

/** Pont de compatibilité entre les défenses historiques et le modèle commun. */
public abstract class Defense extends universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Defense {
    protected Defense(int cout, double x, double y) {
        super(cout, x, y);
    }
}
