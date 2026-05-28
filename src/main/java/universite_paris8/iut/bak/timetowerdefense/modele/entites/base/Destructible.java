package universite_paris8.iut.bak.timetowerdefense.modele.entites.base;

public interface Destructible {
    public int getPv();
    public boolean estMort();
    public void recevoirDegats(int dgt);


}
