package universite_paris8.iut.bak.timetowerdefense.modele.fabriques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.Archer;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.ElPrimo;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.LanceBuche;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.moyenage.def.TourMage;

public class FabriqueMoyenAge implements FabriqueDefense {
    @Override
    public Defense creerDefense(int type, int x, int y) {
        return switch (type) {
            case 1 -> new ElPrimo(x, y);
            case 2 -> new Archer(x, y);
            case 3 -> new LanceBuche(x, y);
            case 4 -> new TourMage(x, y);
            default -> throw new IllegalArgumentException("Type de défense inconnu : " + type);
        };
    }
}