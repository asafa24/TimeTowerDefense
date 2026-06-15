package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.geometry.Point2D;

import java.util.*;

public class Level {
    private int[][][] tileMap;
    private int[][] debutlevel;

    public Level() {

        tileMap = new int[][][]{ {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0},
                {0, 0, 0, 0, 6, 3, 3, 3, 3, 3, 4, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 5, 3, 3, 2, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 5, 3, 3, 2, 0, 0},
                {0, 0, 6, 3, 3, 2, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 5, 3, 2, 0, 0, 1, 0, 0},
                {3, 3, 4, 0, 0, 0, 0, 5, 3, 3, 4, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        },{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 6, 3, 2, 0, 0, 0, 0, 0, 0, 0},
                {7, 2, 0, 1, 0, 5, 3, 3, 3, 3, 2, 0, 0},
                {0, 5, 3, 4, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 6, 3, 3, 3, 3, 3, 3, 3, 4, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 6, 3, 3, 2, 0, 0, 0, 0, 0},
                {0, 0, 5, 3, 4, 0, 0, 5, 3, 3, 2, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}
        },{
                {0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {3, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}
        }, {
                {0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 6, 3, 3, 3, 4, 3, 3, 3, 2, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 5, 3, 2, 0, 0, 0, 6, 3, 4, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 6, 3, 3, 4, 0, 0, 0, 5, 3, 3, 2, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}
        }, {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        }};

        debutlevel = new int[][] {
                {608,32},
                {0,0},
                {0,0},
                {0,0},
                {0,0}

        };
    }


    public int[][] loadLevel(int epoque) {
        return tileMap[epoque];
    }

    public ArrayList<Point2D> calculerChemin(int epoque, Point2D source, Point2D cible) {
        int[][] map = tileMap[epoque];
        ArrayList<Point2D> parcours = new ArrayList<>();
        Map<Point2D, Point2D> predecesseurs = new HashMap<>();
        LinkedList<Point2D> fifo = new LinkedList<>();

        parcours.add(source);
        fifo.add(source);
        predecesseurs.put(source, null);
        while (!fifo.isEmpty()) {
            Point2D s = fifo.removeFirst();
            int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
            List<int[]> directionsList = new ArrayList<>(Arrays.asList(directions));
            Collections.shuffle(directionsList);
            for (int[] dir : directionsList) {
                int nx = (int) s.getX() + dir[0];
                int ny = (int) s.getY() + dir[1];
                if (nx >= 0 && nx < map[0].length && ny >= 0 && ny < map.length) {
                    if (map[ny][nx] >= 1 && map[ny][nx] <= 7) {
                        Point2D t = new Point2D(nx, ny);
                        if (!parcours.contains(t)) {
                            parcours.add(t);
                            fifo.add(t);
                            predecesseurs.put(t, s);
                        }
                    }
                }
            }
        }

        if (!predecesseurs.containsKey(cible)) {
            System.out.println("Erreur : Chemin introuvable");
            return new ArrayList<>();
        }

        ArrayList<Point2D> chemin = new ArrayList<>();
        Point2D actuel = cible;
        while(actuel != null) {
            chemin.add(actuel);
            actuel = predecesseurs.get(actuel);
        }
        Collections.reverse(chemin);
        return chemin;
    }
}
