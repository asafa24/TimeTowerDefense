package universite_paris8.iut.bak.timetowerdefense.modele.fabriques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.ArbreRuste;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.CatapulteCaillou;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.LanceFilet;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.prehistoire.def.MiniVolcan;

public class FabriquePrehistoire implements FabriqueDefense {
    @Override
    public Defense creerDefense(int type, int x, int y) {
        return switch (type) {
            case 1 -> new MiniVolcan(x, y);
            case 2 -> new ArbreRuste(x, y);
            case 3 -> new CatapulteCaillou(x, y);
            case 4 -> new LanceFilet(x, y);
            default -> throw new IllegalArgumentException("Type de défense inconnu : " + type);
        };
    }
}