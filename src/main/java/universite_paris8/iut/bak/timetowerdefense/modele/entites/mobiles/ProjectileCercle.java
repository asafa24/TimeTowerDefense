package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles;

public class ProjectileCercle extends Projectile{
        private int rayonExplosion;
    public ProjectileCercle(double x, double y, Ennemi cible, int degats, int rayonExplosion){
        super(x, y, cible, degats);
        this.rayonExplosion = rayonExplosion;

    }

    public int getRayonExplosion() {
        return rayonExplosion;
    }

}
