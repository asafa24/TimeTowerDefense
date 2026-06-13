package universite_paris8.iut.bak.timetowerdefense.modele.environnement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JeuTest {

    private Jeu jeu;

    @BeforeEach
    void setUp() {
        jeu = new Jeu();
    }

    @Test
    void testInitialisationJeu() {
        assertEquals(200, jeu.getSolde(), "Le solde initial doit être de 200");
        assertEquals(50, jeu.getPvBase(), "Les PV de la base doivent être de 50 au démarrage");
        assertEquals(0, jeu.getEpoqueActuel(), "Le jeu doit commencer à l'époque 0");
        assertFalse(jeu.perdu(), "Le jeu ne doit pas être perdu à l'initialisation");
        assertNotNull(jeu.getEnnemi(), "La liste des ennemis doit être initialisée");
        assertNotNull(jeu.getDefenses(), "La liste des défenses doit être initialisée");
    }

    @Test
    void testGestionArgent() {
        // Ajout
        jeu.ajouterArgent(50);
        assertEquals(250, jeu.getSolde(), "L'ajout d'argent doit fonctionner correctement");

        // Dépense valide
        jeu.depenserArgent(100);
        assertEquals(150, jeu.getSolde(), "La dépense d'argent doit réduire le solde");

        // Dépense invalide (fonds insuffisants)
        jeu.depenserArgent(500);
        assertEquals(150, jeu.getSolde(), "Le solde ne doit pas changer si la dépense dépasse les fonds");
    }

    @Test
    void testConditionDefaite() {
        // On simule des dégâts jusqu'à 0
        jeu.getPvBaseProperty().set(0);
        assertTrue(jeu.perdu(), "Le jeu doit être déclaré perdu quand les PV tombent à 0");

        // On simule des dégâts plus élevés que la vie que l'on a
        jeu.getPvBaseProperty().set(-15);
        assertTrue(jeu.perdu(), "Le jeu doit être déclaré perdu si les PV sont négatifs");
    }

    @Test
    void testChangementEpoque() {
        // Changement valide
        jeu.changerEpoque(2);
        assertEquals(2, jeu.getEpoqueActuel(), "L'époque doit pouvoir être modifiée manuellement");

        // Changement invalide (en dehors des bornes 0-4)
        jeu.changerEpoque(10);
        assertEquals(2, jeu.getEpoqueActuel(), "L'époque ne doit pas changer si l'ID fourni est hors limites");

        jeu.changerEpoque(-1);
        assertEquals(2, jeu.getEpoqueActuel(), "L'époque ne doit pas changer si l'ID est négatif");
    }

    @Test
    void testProgressionEpoqueAutomatique() {
        jeu.prochaineEpoque();
        assertEquals(1, jeu.getEpoqueActuel(), "prochaineEpoque() doit incrémenter l'époque de 1");
    }
}