package universite_paris8.iut.bak.timetowerdefense.modele.entites.effets;

public class EffetProlonge extends DecorateurEffet {
    private final int facteur;

    public EffetProlonge(EffetStatut effet, int facteur) {
        super(effet);
        this.facteur = facteur;
    }

    @Override
    public int getDuree() {
        return effet.getDuree() * facteur;
    }
}
