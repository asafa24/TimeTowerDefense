package universite_paris8.iut.bak.timetowerdefense.modele;

public class Level {
    private int[][][] tileMap;

    public Level() {
        tileMap = new int[5][19][25];
        premierChemin(tileMap);
    }
    public void premierChemin (int [][][] map) {
        int monde = 0;
        int hauteur = 17;
        int largeur = 0 ;
        // ligne 1
        for (int i = 0; i < 5; i++) {
            largeur = i;
            map[monde][hauteur][largeur] = 3;

        }
        map[monde][hauteur][5] = 4;


    }

    public int[][] loadLevel(int epoque) {
        return tileMap[epoque];
    }
}



