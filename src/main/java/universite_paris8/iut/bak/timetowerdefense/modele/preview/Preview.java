package universite_paris8.iut.bak.timetowerdefense.modele.preview;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javafx.scene.paint.Color;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Tour;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

public class Preview {
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private Jeu jeu ;
    private Color color = Color.BLACK;

    private Tour tour;
    private int epoque;

    public Preview(Double x, Double y, Jeu jeu) {
        this.x.set(x);
        this.y.set(y);
        this.jeu = jeu;

    }

    public int getEpoque() {
        return epoque;
    }

    public void setEpoque(int epoque) {
        this.epoque = epoque;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void couleur(){
        if (jeu.piegeloc((int) Math.floor(x.getValue()),(int)Math.floor(y.getValue()) )){
            color =  Color.GREEN;
        }
        else{
            color = Color.RED;
        }
    }
    public void update(double x, double y){
        couleur();
        this.x.set(x);
        this.y.set(y);

    }



    public DoubleProperty xProperty() {
        return x;
    }



    public DoubleProperty yProperty() {
        return y;
    }
}
