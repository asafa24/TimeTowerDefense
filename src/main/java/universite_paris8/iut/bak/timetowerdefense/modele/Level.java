package universite_paris8.iut.bak.timetowerdefense.modele;

public class Level {
    private int[][][] tileMap;

    public Level() {
        tileMap = new int[5][19][25];

    }

    public int[][] loadLevel(int epoque) {
        return tileMap[epoque];
    }


}
