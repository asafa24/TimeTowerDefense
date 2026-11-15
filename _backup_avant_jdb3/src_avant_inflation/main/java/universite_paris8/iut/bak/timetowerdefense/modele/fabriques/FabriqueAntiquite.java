package universite_paris8.iut.bak.timetowerdefense.modele.fabriques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.CatapulteJAR;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.PorteDeSable;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.PyramideShooteuse;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.antiquite.def.TotemFlechette;

public class FabriqueAntiquite implements FabriqueDefense {
    @Override
    public Defense creerDefense(int type, int x, int y) {
        return switch (type) {
            case 1 -> new PorteDeSable(x, y);
            case 2 -> new TotemFlechette(x, y);
            case 3 -> new CatapulteJAR(x, y);
            case 4 -> new PyramideShooteuse(x, y);
            default -> throw new IllegalArgumentException("Type de défense inconnu : " + type);
        };
    }
}