package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Projectile;

import java.util.List;

public class Tour extends Defense {
    private int degats;
    private int portee;
    private int cadence;
    private int compteurTir;
    
    private boolean isStun = false;

    public Tour(int cout ,double x, double y){
        super(cout,x,y);
        this.degats = 25;
        this.portee = 100;
        this.cadence = 120;
        this.compteurTir = cadence/2;
    }

    public Tour(int cout ,double x, double y, int degats, int portee, int cadence){
        super(cout,x,y);
        this.degats = degats;
        this.portee = portee;
        this.cadence = cadence;
        this.compteurTir = 0;
    }

    public void attaquer(List<Ennemi> ennemis, List<Projectile> projectiles){
        if(compteurTir < cadence){
            if(this.isStun == false){
                compteurTir++;    
            }
            return;
        }

        Ennemi cible = trouverCible(ennemis);
        if(cible != null){
            double departX = (getX() * 64) + 32;
            double departY = (getY() * 64) + 32;

            Projectile tir = new Projectile(departX, departY, cible, degats);
            projectiles.add(tir);
            compteurTir = 0;
        }
    }

    public Ennemi trouverCible(List<Ennemi> ennemis){
        Ennemi ciblePlusAvancee = null;
        int etapeMax = -1;
        double centreTx = getX()*64 + 32;
        double centreTy = getY()*64 + 32;

        for(Ennemi e : ennemis){
            double distance = Math.hypot(centreTx - e.getCentreX(), centreTy - e.getCentreY());
            if(distance <= portee){
                if(e.getEtapeActuelle() > etapeMax){
                    etapeMax = e.getEtapeActuelle();
                    ciblePlusAvancee = e;
                }
            }
        }

        return ciblePlusAvancee;
    }
    public int getCadence() {
        return cadence;
    }

    public int getCompteurTir() {
        return compteurTir;
    }

    public int getDegats() {
        return degats;
    }

    public int getPortee() {
        return portee;
    }

    public void setCompteurTir(int compteurTir) {
        this.compteurTir = compteurTir;
    }
    
    public void setStun(boolean stun){
        isStun = stun;
    }
}