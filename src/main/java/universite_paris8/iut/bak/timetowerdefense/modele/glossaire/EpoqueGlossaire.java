package universite_paris8.iut.bak.timetowerdefense.modele.glossaire;

import java.util.List;

public class EpoqueGlossaire {
    private final String nom;
    private final List<EntreeGlossaire> entrees;

    public EpoqueGlossaire(String nom, List<EntreeGlossaire> entrees) {
        this.nom = nom;
        this.entrees = List.copyOf(entrees);
    }

    public String getNom() {
        return nom;
    }

    public List<EntreeGlossaire> getEntrees() {
        return entrees;
    }
}
