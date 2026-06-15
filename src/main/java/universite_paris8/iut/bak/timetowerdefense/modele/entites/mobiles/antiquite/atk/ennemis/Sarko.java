package universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.antiquite.atk.ennemis;

import javafx.geometry.Point2D;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.base.Ennemi;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.mobiles.Boss;
import universite_paris8.iut.bak.timetowerdefense.modele.entites.statiques.Defense;
import universite_paris8.iut.bak.timetowerdefense.modele.environnement.Jeu;

import java.util.List;

public class Sarko extends Boss {
    private Jeu jeu;

    public Sarko(List<Point2D> chemin, Jeu jeu) {
        super(1500, 1, 200, 180, chemin);
        setX(64 * 10);
        setY(64 * 10);
        this.jeu = jeu;
    }

    @Override
    public void competence(List<Ennemi> ennemis, List<Defense> defenses) {
        Ennemi a = new GolemSable((int) Math.floor(this.getX()),(int) Math.floor(this.getY()),this.getChemin());
        a.setEtapeActuelle(this.getEtapeActuelle());

        int e0 = randomInRange(1,5);
        int e1 = randomInRange(1,2);
        int e2 = randomInRange(1,2);
        for (int i = 0; i < e0; i++) {
            jeu.addEnnemi(getEnnemi(10));
        }
        for (int i = 0; i < e1; i++) {
            jeu.addEnnemi(getEnnemi(1));
        }
        for (int i = 0; i < e2; i++) {
            jeu.addEnnemi(getEnnemi(0));
        }



    }

    public Ennemi getEnnemi(int id) {
        int x = (int) Math.floor(this.getX() + randomInRange(-200,200));
        int y = (int) Math.floor(this.getY() + randomInRange(-200,200));
        switch (id) {
            case 1:
                Ennemi a = new GolemSable(x,y,this.getChemin());
                a.setEtapeActuelle(this.getEtapeActuelle());
                return a;

            case 0:
                Ennemi a1 = new Chacal(x,y,this.getChemin());
                a1.setEtapeActuelle(this.getEtapeActuelle());
                return a1;

            default:
                Ennemi a2 = new Momie(x,y,this.getChemin());
                a2.setEtapeActuelle(this.getEtapeActuelle());
                return a2;


        }
    }
    public int randomInRange(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min) + min);
    }
}
