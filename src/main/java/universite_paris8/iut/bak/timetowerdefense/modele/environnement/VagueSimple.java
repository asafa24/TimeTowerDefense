package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import java.util.LinkedList;

import java.util.Queue;

public class VagueSimple implements StrategieVague{

    @Override
    public Queue<Integer> genererFile(int[] compositionVague) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0 ; i < compositionVague.length; i++){

            for (int y = 0 ; y < compositionVague[i] ; y++){
                queue.add(i);
            }

        }
        return queue;
    }
}
