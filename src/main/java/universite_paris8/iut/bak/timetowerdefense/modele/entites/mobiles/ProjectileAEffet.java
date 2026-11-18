package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effets;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

import java.util.ArrayList;
import java.util.List;

public class ProjectileAEffet extends Projectile {
    private int dureeStun;
    private ArrayList<Effets> effets;

    public ProjectileAEffet(double x, double y, Ennemi cible, int degats, int stun , ArrayList<Effets> listeEffet){
        super(x, y, cible, degats);
        this.dureeStun = stun ;
        this.effets = listeEffet ;
    }

    @Override
    public void appliquerImpact(List<Ennemi> ennemis) {
        for (int i = 0; i < effets.size() ; i++ ){
            getCible().ajouterEffet(effets.get(i));
        }
        getCible().recevoirDegats(getDegats());
    }

    public int getDureeStun() {
        return dureeStun;
    }
}