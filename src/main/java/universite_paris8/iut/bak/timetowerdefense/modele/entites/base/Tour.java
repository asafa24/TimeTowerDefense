package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;

import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Tour extends Defense {
    private int degats;
    private int portee;
    private int cadence;
    private int compteurTir;
    
    private boolean isStun = false;
    private IntegerProperty niveau ;
    private int dureeStun = 0;
    // Les durées et compteurs sont en frame

    public Tour(int cout ,double x, double y){
        super(cout,x,y);
        this.degats = 25;
        this.portee = 100;
        this.cadence = 120;
        this.compteurTir = cadence/2;
        niveau = new SimpleIntegerProperty(0);
    }



    public Tour(int cout ,double x, double y, int degats, int portee, int cadence){
        super(cout,x,y);
        this.degats = degats;
        this.portee = portee;
        this.cadence = cadence;
        this.compteurTir = 0;
        niveau = new SimpleIntegerProperty(0);
    }
    public void agir(List<Ennemi> ennemis, List<Projectile> projectiles){
        if (this.isStun) {
            dureeStun--;
            if (dureeStun <= 0) {
                this.isStun = false;
                System.out.println("Plus stun hehe");
            }
            return;
        }

        if(compteurTir < cadence){
            compteurTir++;
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
    
    public void setStun(int dureeStun){
        isStun = true;
        this.dureeStun = dureeStun;
        System.out.println("RAWR (tsais) Tour stun mskn");
    }
    public void amelioration(){
        this.niveau.set(this.niveau.get() + 1);
        this.degats = (int) (this.degats * 1.2);
        this.cadence = (int) (this.cadence / 1.10);
        this.portee = this.portee + 20;
        super.setCout((int) (this.getCout() * 1.30));
    }

    public IntegerProperty niveauProperty() {
        return niveau;
    }

    public int getNiveau() {
        return niveau.get();
    }
    public abstract void inflation();

}