package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import java.util.ArrayList;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Filet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourStun;

public class LanceFilet extends TourStun {
    public static IntegerProperty cout = new SimpleIntegerProperty(125);


    public LanceFilet(double x, double y) {
        super(cout.get(), x, y, 5, 150, 70, 60);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int dureeStun) {
        ArrayList<Effet> effets = new ArrayList<>();
        effets.add(Effet.STUN);
        return new Filet(x, y, cible, getDegats(), getDureeStun());
    }

    @Override
    public void inflation(){
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }
    public static IntegerProperty coutPropertyLanceFilet() {
        return cout;
    }
}