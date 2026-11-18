package universite_paris8.iut.bak.timetowerdefense.modele.fabriques;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Defense;

public interface FabriqueDefense {
    Defense creerDefense(int type, int x, int y);
}