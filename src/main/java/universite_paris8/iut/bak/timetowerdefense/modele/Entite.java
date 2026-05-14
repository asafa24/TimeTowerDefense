package universite_paris8.iut.bak.timetowerdefense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Entite {
    private static int compteurId = 0;
    private DoubleProperty x = new SimpleDoubleProperty();
    private DoubleProperty y = new SimpleDoubleProperty();
    private int id;

    public Entite(double x, double y){
        this.x.set(x);
        this.y.set(y);
        this.id = compteurId;
        compteurId++;
    }

    public double getX(){
        return x.get();
    }

    public double getY(){
        return y.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }
    public DoubleProperty yProperty() {
        return y;
    }


    public void setX(double x){
        this.x.set(x);
    }

    public void setY(double y){
        this.y.set(y);
    }

    public int getId(){
        return id;
    }


}
