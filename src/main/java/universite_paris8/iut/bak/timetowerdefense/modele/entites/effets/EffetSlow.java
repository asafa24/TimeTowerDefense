package universite_paris8.iut.bak.timetowerdefense.modele.entites.effets;

public class EffetSlow implements EffetStatut {
    @Override
    public int getDuree() {
        return 120;
    }

    @Override
    public double modifierVitesse(double vitesse) {
        return vitesse * 0.5;
    }
}