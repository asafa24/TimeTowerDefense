package universite_paris8.iut.bak.timetowerdefense.modele.entites.effets;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;

public abstract class DecorateurEffet implements EffetStatut {
    protected final EffetStatut effet;

    public DecorateurEffet(EffetStatut effet) {
        this.effet = effet;
    }

    @Override
    public int getDuree() {
        return effet.getDuree();
    }

    @Override
    public double modifierVitesse(double vitesse) {
        return effet.modifierVitesse(vitesse);
    }

    @Override
    public void tick(Ennemi ennemi, int dureeRestante) {
        effet.tick(ennemi, dureeRestante);
    }

    @Override
    public void surApplication(Ennemi ennemi) {
        effet.surApplication(ennemi);
    }
}
