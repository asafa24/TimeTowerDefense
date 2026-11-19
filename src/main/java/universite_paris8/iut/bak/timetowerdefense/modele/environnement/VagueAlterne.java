package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import java.util.LinkedList;
import java.util.Queue;


public class VagueAlterne implements StrategieVague {

    @Override
    public Queue<Integer> genererFile(int[] compositionVague) {
        int[] restants = compositionVague.clone();
        Queue<Integer> queue = new LinkedList<>();

        boolean resteDesEnnemis = true;
        while (resteDesEnnemis) {
            resteDesEnnemis = false;
            for (int type = 0; type < restants.length; type++) {
                if (restants[type] > 0) {
                    queue.add(type);
                    restants[type]--;
                    resteDesEnnemis = true;
                }
            }
        }
        return queue;
    }
}
