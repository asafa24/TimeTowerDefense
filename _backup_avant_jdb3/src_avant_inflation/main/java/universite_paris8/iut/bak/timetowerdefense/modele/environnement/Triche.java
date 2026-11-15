package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.animation.Timeline;

public class Triche {

    private Jeu jeu;

    public Triche(Jeu jeu) {
        this.jeu = jeu;
    }

    public void tricheArgent(int montant) {
        jeu.ajouterArgent(montant);
        System.out.println("[TRICHE] Ajout de " + montant + " pièces.");
    }

    public void tricheVague() {
        jeu.getVague().vagueSuivante();
        System.out.println("[TRICHE] Vague passée");
    }
    public void tricheKills(int nbKills) {
        jeu.getCompteurKillProperty().set(jeu.getCompteurKillProperty().get() + nbKills);
        System.out.println("[TRICHE] Ajout de " + nbKills + " kills.");
    }
    public void tricheChangeEpoque(int nouvelleEpoque) {
        if (nouvelleEpoque >= 0 && nouvelleEpoque <= 5) {
            jeu.setEpoqueActuel(nouvelleEpoque);
            jeu.nuke();
            jeu.newRoute();
            jeu.actualiserUltime();
            jeu.resetTimersEtVague();
            System.out.println("[TRICHE] Époque changé à : " + nouvelleEpoque);
        }
    }
    public boolean toggleSpeedBoost(Timeline gameLoop) {
        if (gameLoop.getRate() == 1.0) {
            gameLoop.setRate(2.5);
            System.out.println("[TRICHE] Vitesse x2.5");
            return true;
        } else {
            gameLoop.setRate(1.0);
            System.out.println("[TRICHE] Vitesse x1");
            return false;
        }
    }

}