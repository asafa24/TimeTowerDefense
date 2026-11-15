package universite_paris8.iut.bak.timetowerdefense.modele.entites.effets;

public class EffetStun implements EffetStatut {
    @Override
    public int getDuree() {
        return 120;
    }

    @Override
    public double modifierVitesse(double vitesse) {
        return 0;
    }
}