package universite_paris8.iut.bak.timetowerdefense.modele.fabriques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def.LanceRocket;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def.Mine;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def.Sniper;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.contemporain.def.SoldatBleu;

public class FabriqueContemporain implements FabriqueDefense {
    @Override
    public Defense creerDefense(int type, int x, int y) {
        return switch (type) {
            case 1 -> new Mine(x, y);
            case 2 -> new SoldatBleu(x, y);
            case 3 -> new Sniper(x, y);
            case 4 -> new LanceRocket(x, y);
            default -> throw new IllegalArgumentException("Type de défense inconnu : " + type);
        };
    }
}