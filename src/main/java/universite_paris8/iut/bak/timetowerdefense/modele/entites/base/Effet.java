package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

import universite_paris8.iut.bak.timetowerdefense.modele.entites.effets.*;

public enum Effet {
    STUN(new EffetStun()),
    BURN(new EffetBrulure()),
    SLOW(new EffetSlow()),
    SHIELD(new EffetBouclier()),
    SPEED_BOOST(new EffetSpeedBoost()),
    BURN_LONG(new EffetProlonge(new EffetBrulure(), 2));

    private final EffetStatut strategie;

    Effet(EffetStatut strategie) {
        this.strategie = strategie;
    }

    public EffetStatut getStrategie() {
        return strategie;
    }
}
