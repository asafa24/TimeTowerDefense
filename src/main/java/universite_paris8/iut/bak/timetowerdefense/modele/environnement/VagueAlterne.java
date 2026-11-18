package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Stratégie qui alterne chaque type d'ennemi : un ennemi de chaque type
 * (0, 1, 2, 3...) à tour de rôle, tant qu'il en reste à envoyer.
 */
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
