package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Effet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import java.util.ArrayList;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.prehistoire.atk.projectiles.Filet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourStun;

public class LanceFilet extends TourStun {
    private static final int PRIX_BASE = 125;

    public LanceFilet(double x, double y) {
        super(PRIX_BASE, x, y, 5, 150, 120, 60);
    }

    public static int getPrixBase() {
        return prixActuel(LanceFilet.class, PRIX_BASE);
    }

    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int dureeStun) {
        ArrayList<Effet> effets = new ArrayList<>();
        effets.add(Effet.STUN);
        return new Filet(x, y, cible, getDegats(), getDureeStun());
    }
}
