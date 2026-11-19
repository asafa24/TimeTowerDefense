package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class VagueShuffle implements StrategieVague {

    @Override
    public Queue<Integer> genererFile(int[] compositionVague) {
        List<Integer> ennemis = new ArrayList<>();
        for (int type = 0; type < compositionVague.length; type++) {
            for (int i = 0; i < compositionVague[type]; i++) {
                ennemis.add(type);
            }
        }
        Collections.shuffle(ennemis);
        return new LinkedList<>(ennemis);
    }
}
