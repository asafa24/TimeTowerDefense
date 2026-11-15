package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

import java.util.ArrayList;
import java.util.List;

public class ProjectileAEffet extends Projectile {
    private int dureeStun;
    private ArrayList<Effet> effets;

    public ProjectileAEffet(double x, double y, Ennemi cible, int degats, int stun , ArrayList<Effet> listeEffet){
        super(x, y, cible, degats);
        this.dureeStun = stun ;
        this.effets = listeEffet ;
    }

    @Override
    public void appliquerImpact(List<Ennemi> ennemis) {
        for (int i = 0; i < effets.size() ; i++ ){
            getCible().appliqueEffet(effets.get(i));
        }
        getCible().recevoirDegats(getDegats());
    }

    public int getDureeStun() {
        return dureeStun;
    }
}