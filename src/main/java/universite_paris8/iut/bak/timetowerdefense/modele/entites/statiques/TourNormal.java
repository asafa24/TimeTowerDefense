package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;

import java.util.List;

public abstract class TourNormal extends Tour {
    public TourNormal(int cout ,double x, double y, int degats, int portee, int cadence){
        super(cout, x, y, degats, portee, cadence);
    }

    @Override
    public abstract void inflation();

    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles){
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
            Projectile tir = creerProjectile(departX, departY, cible);
            projectiles.add(tir);
            super.setCompteurTir(0);
        }
    }
    protected abstract Projectile creerProjectile(double x, double y, Ennemi cible );

}
