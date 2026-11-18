package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import java.util.Queue;

public interface StrategieVague {
    Queue<Integer> genererFile(int[] compositionVague);
}
