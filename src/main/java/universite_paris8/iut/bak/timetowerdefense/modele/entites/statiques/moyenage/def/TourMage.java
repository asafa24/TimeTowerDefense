package universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Projectile;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.moyenage.atk.projectiles.Sort;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.TourStun;

public class TourMage extends TourStun {
    private static final int PRIX_BASE = 130 ;
    public TourMage(double x, double y) {
        super(PRIX_BASE, x, y, 15, 150, 150, 50);
    }
    @Override
    protected Projectile creerProjectile(double x, double y, Ennemi cible, int degats, int dureeStun) {
        return new Sort(x, y, cible, degats, dureeStun);
    }
    public static int getPrixBase() {
        return prixActuel(TourMage.class, PRIX_BASE);
    }























}
