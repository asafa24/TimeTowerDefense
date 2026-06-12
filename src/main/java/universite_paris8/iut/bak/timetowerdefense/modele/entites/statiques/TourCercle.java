package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;

import java.util.List;

public abstract class TourCercle extends Tour {
    private int rayonExplosion;

    public TourCercle(int cout ,double x, double y, int degats, int portee, int cadence, int rayonExplosion){
        super(cout, x, y, degats, portee, cadence);
        this.rayonExplosion = rayonExplosion;
    }


    @Override
    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles) {
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

            Projectile tir = creerProjectile(departX, departY, cible, getDegats(), rayonExplosion);
            projectiles.add(tir);
            setCompteurTir(0);
        }
    }
    @Override
    public abstract void inflation();
    protected abstract Projectile creerProjectile(double x, double y, Ennemi cible ,int degats , int rayonExplosion );



}
