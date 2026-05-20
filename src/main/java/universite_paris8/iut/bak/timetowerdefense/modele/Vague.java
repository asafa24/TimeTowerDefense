package universite_paris8.iut.bak.timetowerdefense.modele;

import java.util.Queue;
import java.util.LinkedList;

public class Vague {
    private int[][] level1;
    private int vague;
    Queue<Integer>[] queue;

    public Vague() {
        level1 = new int[][]{
                {5,0,0,0},   // vague 1
                {10,0,0,0},  // vague 2
                {20,2,0,0},  // vague 3
                {30,3,1,0},  // vague 4
                {40,4,2,0},  // vague 5
                {45,5,3,0},  // vague 6
                {50,6,3,0},  // vague 7
                {55,7,4,0},  // vague 8
                {60,10,6,0}, // vague 9
                {75,12,8,1}  // vague 10 avec le boss (nn c'est toi tié le boss)
        };
        vague = 0;
        queue = new Queue[level1.length];
        for (int i = 0; i < level1.length; i++) {
            queue[i] = laQueue(level1[i]);
        }
    }
    public void vagueSuivante(){
        vague++;

    }

    public void test() {
        for (int i = 0; i < queue.length; i++) {


            System.out.println("Test vague : " + i +  queue[i].toString() );
        }
    }
    public int defiler(){
        System.out.println(queue[vague].toString());
        return queue[vague].remove();
    }

    public Queue<Integer> getQueue(){
        return queue[vague];
    }

    public int somme(int[] tab) {
        int somme = 0;
        for (int nb : tab) {
            somme += nb;
        }
        return somme;
    }

    public Queue<Integer> laQueue(int[] tabOriginal) {

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
}