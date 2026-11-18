package universite_paris8.iut.bak.timetowerdefense.modele.glossaire;

public class EntreeGlossaire {
    private final String nom;
    private final String description;
    private final CategorieGlossaire categorie;

    public EntreeGlossaire(String nom, String description, CategorieGlossaire categorie) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public CategorieGlossaire getCategorie() {
        return categorie;
    }
}
