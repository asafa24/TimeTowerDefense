package universite_paris8.iut.bak.timetowerdefense.modele.preview;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Piege;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

public class Preview {
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private Jeu jeu ;
    private BooleanProperty peut ;
    private boolean piege;
    private int id;
    private int epoque;

    public Preview(Double x, Double y, Jeu jeu, int id) {
        this.x.set(x);
        this.y.set(y);
        this.jeu = jeu;
        this.peut = new SimpleBooleanProperty(false);
        this.id = id;
        this.epoque = 0;
        this.piege = false;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDefense(){
        if(id != 1){
            piege = false;
            //System.out.println("id = " + id);
        }
        else {
            piege = true;

        }
    }

    public int getEpoque() {
        return epoque;
    }

    public void setEpoque(int epoque) {
        this.epoque = epoque;
    }


    public boolean isPeut() {
        return peut.get();
    }

    public BooleanProperty peutProperty() {
        return peut;
    }

    public void couleur(){
        if (id != 1){
            this.peut.set(jeu.peuxPoserTourCoord((int) Math.floor(x.get() / 64), (int) Math.floor(y.get() / 64) ));
        }
        else {
            this.peut.set(jeu.peuxPoserPiegeCoord((int) Math.floor(x.get() / 64), (int) Math.floor(y.get() / 64) ));


        }
    }
    public void update(double x, double y, int id){
        setId(id);
        this.x.set(x);
        this.y.set(y);
        setDefense();
        couleur();


    }


    public DoubleProperty xProperty() {
        return x;
    }



    public DoubleProperty yProperty() {
        return y;
    }
}
