package universite_paris8.iut.bak.timetowerdefense.modele;

import java.util.Queue;
import java.util.PriorityQueue;

public class Vague {
    public int[][] level1 ;

    public Vague(){
        level1 = new int[][]{
                {5,0,0,0}, // vague1
                {10,0,0,0}, // vague 2
                {20,2,0,0}, // vague 3
                {30,3,1,0}, // vague 4
                {40,4,2,0}, // vague 5
                {45,5,3,0}, // vague 6
                {50,6,3,0}, // vague 7
                {55,7,4,0}, // vague 8
                {60,10,6,0}, // vague 9
                {75,12,8,1} // vague 10 avec le boss ( nn c'est toi tié le boss )

        };



    }
    public int somme(int[] tab){
        int somme = 0;
        for (int nb : tab){
            somme += nb;
        }
        return somme;
    }

    public Queue<Integer> laQueue(int[] tab){
        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < somme(tab) - 1 ; i++){

        }

        return queue;
    }

}
