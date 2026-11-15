package universite_paris8.iut.bak.timetowerdefense.modele.entites.effets;

public class EffetSpeedBoost implements EffetStatut {
    @Override
    public int getDuree() {
        return 150;
    }

    @Override
    public double modifierVitesse(double vitesse) {
        return vitesse * 1.3;
    }
}