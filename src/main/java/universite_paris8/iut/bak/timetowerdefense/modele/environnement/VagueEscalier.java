package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import java.util.LinkedList;
import java.util.Queue;

public class VagueEscalier implements StrategieVague {

    @Override
    public Queue<Integer> genererFile(int[] tabOriginal) {

        int[] tab = tabOriginal.clone();


        Queue<Integer> queue = new LinkedList<>();


        int compteur0 = 0;
        int compteur1 = 0;

        while (somme(tab) > 0) {


            if (compteur1 >= 2 && tab[2] > 0) {
                queue.add(2);
                tab[2]--;
                compteur1 = 0;
            }

            else if (compteur0 >= 10 && tab[1] > 0) {
                queue.add(1);
                tab[1]--;
                compteur0 = 0;
                compteur1++;
            }

            else if (tab[0] > 0) {
                queue.add(0);
                tab[0]--;
                compteur0++;
            }

            else {
                if (tab[3] > 0){
                    queue.add(3);
                    tab[3]--;
                }
                else if (tab[1] > 0) {
                    queue.add(1);
                    tab[1]--;
                    compteur1++;
                } else if (tab[2] > 0) {
                    queue.add(2);
                    tab[2]--;
                    compteur1 = 0;
                }
            }
        }

        return queue;
    }

    public int somme(int[] tab) {
        int somme = 0;
        for (int nb : tab) {
            somme += nb;
        }
        return somme;
    }
}