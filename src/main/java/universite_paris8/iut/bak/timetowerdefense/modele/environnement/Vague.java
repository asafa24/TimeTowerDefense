package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Queue;
import java.util.Random;

public class Vague {
    // Vagues (numérotées à partir de 1) pour lesquelles la stratégie est tirée au hasard
    private static final int PREMIERE_VAGUE_ALEATOIRE = 3;
    private static final int DERNIERE_VAGUE_ALEATOIRE = 8;

    private final int[][] effectifs;
    private final IntegerProperty vague;
    private final Random random = new Random();
    private final StrategieVague strategieParDefaut = new VagueEscalier();
    private final StrategieVague[] strategiesPossibles = {
            new VagueSimple(),
            new VagueEscalier(),
            new VagueAlterne(),
            new VagueShuffle()
    };
    Queue<Integer>[] queue;

    public Vague(int[][] effectifs) {
        this.effectifs = effectifs;
        this.vague = new SimpleIntegerProperty(0);
        genererQueues();
    }

    private StrategieVague choisirStrategie(int indexVague) {
        int numeroVague = indexVague + 1;
        if (numeroVague >= PREMIERE_VAGUE_ALEATOIRE && numeroVague <= DERNIERE_VAGUE_ALEATOIRE) {
            return strategiesPossibles[random.nextInt(strategiesPossibles.length)];
        }
        return strategieParDefaut;
    }

    @SuppressWarnings("unchecked")
    private void genererQueues() {
        this.queue = new Queue[effectifs.length];
        for (int i = 0; i < effectifs.length; i++) {
            this.queue[i] = choisirStrategie(i).genererFile(effectifs[i]);
        }
    }

    public boolean vagueSuivante() {
        if (vague.get() < effectifs.length - 1) {
            vague.set(vague.get() + 1);
            return true;
        }
        return false;
    }

    public void test() {
        for (int i = 0; i < queue.length; i++) {
            System.out.println("Test vague : " + i + queue[i].toString());
        }
    }

    public int defiler() {
        System.out.println(queue[vague.get()].toString());
        if (queue[vague.get()].size() == 0) {
            return -1;
        }
        return queue[vague.get()].remove();
    }

    public Queue<Integer> getQueue() {
        return queue[vague.get()];
    }

    public void reinitialiser() {
        this.vague.set(0);
        genererQueues();
    }

    public int getVague() {
        return vague.get();
    }

    public IntegerProperty getVagueProperty() {
        return vague;
    }
}
