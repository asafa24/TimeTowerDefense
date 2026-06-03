package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

public class ProjectileStun extends Projectile {
    private int dureeStun;
    public ProjectileStun(double x, double y, Ennemi cible, int degats, int stun){
        super(x, y, cible, degats);
        this.dureeStun = stun ;
    }

    public int getDureeStun() {
        return dureeStun;
    }
}
