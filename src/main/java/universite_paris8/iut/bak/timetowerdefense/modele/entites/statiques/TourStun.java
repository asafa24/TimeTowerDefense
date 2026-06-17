package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

import java.util.List;

public abstract class TourStun extends Tour {
    private int dureeStun;
    public static final int PRIX_ACHAT = 125;

    public TourStun(int cout, double x, double y, int degats, int portee, int cadence, int dureeStun){
        super(cout, x, y, degats, portee, cadence);
        this.dureeStun = dureeStun;
    }

    @Override
    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles){
        if(!getActif()) return;

        if (isStunOuPas()) {
            return;
        }

        if(getCompteurTir() < getCadence()){
            setCompteurTir(getCompteurTir()+1);
            return;
        }

        Ennemi cible = trouverCible(ennemis);
        if(cible != null){
            double departX = (getX() * 64) + 32;
            double departY = (getY() * 64) + 32;

            Projectile tir = creerProjectile(departX, departY, cible ,super.getDegats() ,dureeStun);

            if (tir != null) {
                projectiles.add(tir);
            }
            setCompteurTir(0);
        }
    }

    protected abstract Projectile creerProjectile(double x, double y, Ennemi cible ,int degats , int dureeStun);

    public int getDureeStun() {
        return dureeStun;
    }

    public abstract void inflation();
}