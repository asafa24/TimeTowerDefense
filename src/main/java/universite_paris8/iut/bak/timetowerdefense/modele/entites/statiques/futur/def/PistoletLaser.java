package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.futur.def;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.futur.atk.projectile.ProjectileLazer;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourNormal;

public class PistoletLaser extends TourNormal {
    public static IntegerProperty cout = new SimpleIntegerProperty(60);


    public PistoletLaser(double x, double y) {
        super(cout.get(), x, y, 35, 150, 60);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible) {
        return new ProjectileLazer(x, y, cible, getDegats());
    }

    @Override
    public void inflation() {
        setCout((int) (getCout() * 1.2));
    }
    public static IntegerProperty pistoLaser() {
        return cout;
    }

}