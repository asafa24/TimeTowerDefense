package universite_paris8.iut.bak.timetowerdefense.modele.fabriques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.PistoletLaser;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.PistoletLazerMk2;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.Tesla;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.future.def.TourDuTemps;

public class FabriqueFutur implements FabriqueDefense {
    @Override
    public Defense creerDefense(int type, int x, int y) {
        return switch (type) {
            case 1 -> new PistoletLaser(x, y);
            case 2 -> new PistoletLazerMk2(x, y);
            case 3 -> new Tesla(x, y);
            case 4 -> new TourDuTemps(x, y);
            default -> throw new IllegalArgumentException("Type de défense inconnu : " + type);
        };
    }
}