package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.competences.Ultime;
import java.util.*;

public abstract class EpoqueSimple implements Epoque {

    private int[][] tileMap;
    private Point2D[] departs;
    private Point2D arrivee;
    private Vague vague;
    private Ultime ultime;

    public EpoqueSimple(int[][] tileMap, Point2D[] departs, Point2D arrivee,
            int[][] effectifs, Ultime ultime) {

        this.tileMap = tileMap;
        this.departs = departs;
        this.arrivee = arrivee;
        this.vague = new Vague(effectifs);
        this.ultime = ultime;
    }

    @Override
    public Vague getVague() {
        return this.vague;
    }

    @Override
    public Ultime getUltime() {
        return this.ultime;
    }

    @Override
    public boolean coordonneeValide(int x, int y) {
        return y >= 0 && y < this.tileMap.length
                && x >= 0 && x < this.tileMap[y].length;
    }

    @Override
    public int valeurCoordonnee(int x, int y) {

        if (!coordonneeValide(x, y)) {
            return -1;
        }
        return this.tileMap[y][x];
    }

    @Override
    public int hauteurMap() {
        return this.tileMap.length;
    }

    @Override
    public int longueurMap() {
        return this.tileMap[0].length;
    }

    @Override
    public List<Point2D> cheminDepartArrivee() {
        Point2D depart = this.departs[(int) (Math.random() * this.departs.length)];
        return calculerChemin(depart, this.arrivee);
    }

    public List<Point2D> calculerChemin(Point2D source, Point2D cible) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EpoqueSimple other = (EpoqueSimple) obj;
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
