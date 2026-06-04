package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.ProjectileStun;

import java.util.List;

public class TourStun extends Tour {
    private int dureeStun;
    public static final int PRIX_ACHAT = 125 ;
    public TourStun(int cout ,double x, double y, int degats, int portee, int cadence, int dureeStun){
        super(cout, x, y, degats, portee, cadence);
        this.dureeStun = dureeStun;
    }
    public TourStun(int cout ,double x, double y, int degats, int portee, int cadence, int dureeStun,String sprite){
        super(cout, x, y, degats, portee, cadence, sprite);
        this.dureeStun = dureeStun;
    }
    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles){
        if(getCompteurTir() < getCadence()){
            setCompteurTir(getCompteurTir()+1);
            return;
        }

        Ennemi cible = trouverCible(ennemis);
        if(cible != null){
            double departX = (getX() * 64) + 32;
            double departY = (getY() * 64) + 32;

            Projectile tir = new ProjectileStun(departX, departY, cible, getDegats(), this.dureeStun);
            projectiles.add(tir);
            setCompteurTir(0);
        }
    }

    public int getDureeStun() {
        return dureeStun;
    }
}
