package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.geometry.Point2D;

import java.util.*;

public abstract class Epoque {
    private int[][] /* [] */ tileMap;
    // private int[][] debutlevel;
    private Point2D[] departs;
    private Point2D arrivee;

    public Epoque(int[][] tileMap, Point2D[] departs, Point2D arrivee) {

        this.tileMap = tileMap;
        this.departs = departs;
        this.arrivee = arrivee;

        // tileMap = new int[][][] { {
        // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0 },
        // { 0, 0, 0, 0, 6, 3, 3, 3, 3, 3, 4, 0, 0 },
        // { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 5, 3, 3, 2, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0, 5, 3, 3, 2, 0, 0 },
        // { 0, 0, 6, 3, 3, 2, 0, 0, 0, 0, 1, 0, 0 },
        // { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
        // { 0, 0, 1, 0, 0, 5, 3, 2, 0, 0, 1, 0, 0 },
        // { 3, 3, 4, 0, 0, 0, 0, 5, 3, 3, 4, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        // }, {
        // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 6, 3, 2, 0, 0, 0, 0, 0, 0, 0 },
        // { 7, 2, 0, 1, 0, 5, 3, 3, 3, 3, 2, 0, 0 },
        // { 0, 5, 3, 4, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
        // { 0, 0, 6, 3, 3, 3, 3, 3, 3, 3, 4, 0, 0 },
        // { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 1, 0, 6, 3, 3, 2, 0, 0, 0, 0, 0 },
        // { 0, 0, 5, 3, 4, 0, 0, 5, 3, 3, 2, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }
        // }, {
        // { 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        // { 3, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }
        // }, {
        // { 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 6, 3, 3, 3, 8, 3, 3, 3, 2, 0, 0 },
        // { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
        // { 0, 0, 5, 3, 2, 0, 0, 0, 6, 3, 4, 0, 0 },
        // { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0 },
        // { 0, 6, 3, 3, 4, 0, 0, 0, 5, 3, 3, 2, 0 },
        // { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
        // { 0, 5, 3, 3, 3, 3, 9, 3, 3, 3, 3, 4, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }
        // }, {
        // { 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 6, 3, 3, 3, 9, 3, 3, 3, 2, 0, 0 },
        // { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
        // { 6, 3, 9, 3, 2, 0, 0, 0, 6, 3, 9, 3, 2 },
        // { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
        // { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
        // { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
        // { 5, 3, 8, 3, 4, 0, 0, 0, 5, 3, 8, 3, 4 },
        // { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
        // { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }
        // } };

        // debutlevel = new int[][] {
        // { 608, 32 },
        // { 0, 0 },
        // { 0, 0 },
        // { 0, 0 },
        // { 0, 0 }

        // };
    }

    // public int[][] loadLevel(int epoque) {
    // return tileMap[epoque];
    // }

    public boolean coordonneeValide(int x, int y) {
        return y >= 0 && y < this.tileMap.length
                && x >= 0 && x < this.tileMap[y].length;
    }

    // TODO remplacer par estChemin, estTruc ?
    public int valeurCoordonnee(int x, int y) {

        if (!coordonneeValide(x, y)) {
            return -1;
        }
        return this.tileMap[y][x];
    }

    public int hauteurMap() {
        return this.tileMap.length;
    }

    public int longueurMap() {
        return this.tileMap[0].length;
    }

    public List<Point2D> calculerChemin(/* int epoque, */ Point2D source, Point2D cible) {
        // int[][] map = tileMap[epoque];
        List<Point2D> parcours = new ArrayList<>();
        Map<Point2D, Point2D> predecesseurs = new HashMap<>();
        LinkedList<Point2D> fifo = new LinkedList<>();

        parcours.add(source);
        fifo.add(source);
        predecesseurs.put(source, null);
        while (!fifo.isEmpty()) {
            Point2D s = fifo.removeFirst();
            int[][] directions = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
            List<int[]> directionsList = new ArrayList<>(Arrays.asList(directions));
            Collections.shuffle(directionsList);
            for (int[] dir : directionsList) {
                int nx = (int) s.getX() + dir[0];
                int ny = (int) s.getY() + dir[1];
                if (nx >= 0 && nx < this.tileMap[0].length && ny >= 0 && ny < this.tileMap.length) {
                    if (this.tileMap[ny][nx] >= 1 && this.tileMap[ny][nx] <= 9) {
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

        List<Point2D> chemin = new ArrayList<>();
        Point2D actuel = cible;
        while (actuel != null) {
            chemin.add(actuel);
            actuel = predecesseurs.get(actuel);
        }
        Collections.reverse(chemin);
        return chemin;
    }

    public List<Point2D> cheminDepartArrivee() {
        Point2D depart = this.departs[(int) (Math.random() * this.departs.length)];
        return calculerChemin(depart, this.arrivee);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Epoque other = (Epoque) obj;
        if (!Arrays.deepEquals(tileMap, other.tileMap))
            return false;
        if (!Arrays.equals(departs, other.departs))
            return false;
        if (arrivee == null) {
            if (other.arrivee != null)
                return false;
        } else if (!arrivee.equals(other.arrivee))
            return false;
        return true;
    }

}
