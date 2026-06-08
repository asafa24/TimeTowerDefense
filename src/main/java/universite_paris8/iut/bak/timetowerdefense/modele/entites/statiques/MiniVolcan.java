package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

import java.util.List;

public class MiniVolcan extends Piege {
    private int degats;
    public static IntegerProperty cout = new SimpleIntegerProperty(25);

    private int cooldownDegats = 0;

    public MiniVolcan(int cout, double x, double y, int degats, int pv) {
        super(cout, x, y, pv);
        this.degats = degats;
    }

    public MiniVolcan(double x, double y) {
        super(cout.get(), x, y, 5);
        this.degats = 5;
    }

    @Override
    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles){
        if (cooldownDegats > 0) {
            cooldownDegats--;
        }

        for (int i = ennemis.size() - 1; i >= 0; i--) {
            Ennemi e = ennemis.get(i);
            if (aAtteintPiege(e)) {
                e.appliqueEffet(Effet.SLOW);
                e.appliqueEffet(Effet.BURN);

                if (getPv() > 0 && cooldownDegats <= 0) {
                    recevoirDegats(1);
                    System.out.println("Le volcan a brûlé un ennemi ! PV restants : " + getPv());

                    cooldownDegats = 60;
                }
            }
        }
    }

    @Override
    public void inflation(){
        cout.set((int) Math.floor(cout.get() * 1.1));
        super.setCout(cout.get());
    }

    public static IntegerProperty coutPropertyArbre() {
        return cout;
    }
}