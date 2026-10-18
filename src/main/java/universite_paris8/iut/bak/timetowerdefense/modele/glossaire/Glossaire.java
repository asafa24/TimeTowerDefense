package universite_paris8.iut.bak.timetowerdefense.modele.glossaire;

import java.util.List;
import java.util.Map;

// Catalogue des informations du glossaire, indexé par époque du jeu.
public final class Glossaire {
    private static final EpoqueGlossaire EPOQUE_INCONNUE = new EpoqueGlossaire("Époque inconnue", List.of());

    private static final Map<Integer, EpoqueGlossaire> EPOQUES = Map.of(
            0, new EpoqueGlossaire("Préhistoire", List.of(
                    entree("Mini Volcan", "Piège qui inflige des dégâts de zone et brûle les ennemis.", CategorieGlossaire.DEFENSE),
                    entree("Arbre Rustique", "Tour de base qui tire des projectiles sur les ennemis.", CategorieGlossaire.DEFENSE),
                    entree("Catapulte à Cailloux", "Catapulte qui inflige de lourds dégâts à l'impact.", CategorieGlossaire.DEFENSE),
                    entree("Lance-Filet", "Tour qui étourdit les ennemis avec ses filets.", CategorieGlossaire.DEFENSE),
                    entree("Compsognathus", "Petit dinosaure qui se déplace rapidement.", CategorieGlossaire.ENNEMI),
                    entree("Vélociraptor", "Dinosaure rapide qui fonce vers la base.", CategorieGlossaire.ENNEMI),
                    entree("Tricératops", "Protège les ennemis proches en leur donnant un bouclier.", CategorieGlossaire.ENNEMI_RESISTANT),
                    entree("Tyrannosaure (Boss)", "Boss qui peut étourdir les défenses avec son rugissement.", CategorieGlossaire.BOSS)
            )),
            1, new EpoqueGlossaire("Antiquité", List.of(
                    entree("Porte de Sable", "Piège destructible qui bloque les ennemis sur leur route.", CategorieGlossaire.DEFENSE),
                    entree("Totem à Fléchettes", "Tour qui empoisonne les ennemis touchés.", CategorieGlossaire.DEFENSE),
                    entree("Catapulte à Jarres", "Catapulte qui lance des jarres explosives.", CategorieGlossaire.DEFENSE),
                    entree("Pyramide Shooteuse", "Tour qui attaque les ennemis avec un rayon.", CategorieGlossaire.DEFENSE),
                    entree("Momie", "Ennemi résistant qui avance lentement.", CategorieGlossaire.ENNEMI),
                    entree("Chacal", "Ennemi rapide qui suit la route vers la base.", CategorieGlossaire.ENNEMI),
                    entree("Golem de Sable", "Créature massive qui se divise en deux Golimes à sa mort.", CategorieGlossaire.ENNEMI_RESISTANT),
                    entree("Golime", "Petit fragment issu de la destruction d'un Golem de Sable.", CategorieGlossaire.ENNEMI),
                    entree("Sarko (Boss)", "Boss de l'époque antique.", CategorieGlossaire.BOSS)
            )),
            2, new EpoqueGlossaire("Moyen Âge", List.of(
                    entree("El Primo", "Piège défensif placé sur le chemin des ennemis.", CategorieGlossaire.DEFENSE),
                    entree("Archer", "Tour qui attaque les ennemis à distance.", CategorieGlossaire.DEFENSE),
                    entree("Lance-Bûche", "Tour qui lance des bûches sur les ennemis.", CategorieGlossaire.DEFENSE),
                    entree("Tour Mage", "Tour qui étourdit les ennemis qu'elle touche.", CategorieGlossaire.DEFENSE),
                    entree("Moine", "Ennemi qui avance vers la base.", CategorieGlossaire.ENNEMI),
                    entree("Chevalier", "Ennemi protégé par son armure.", CategorieGlossaire.ENNEMI_RESISTANT),
                    entree("Cavalier", "Ennemi monté qui progresse rapidement sur la route.", CategorieGlossaire.ENNEMI),
                    entree("Roi (Boss)", "Boss de l'époque médiévale.", CategorieGlossaire.BOSS)
            )),
            3, new EpoqueGlossaire("Époque contemporaine", List.of(
                    entree("Mine", "Piège explosif placé sur le chemin des ennemis.", CategorieGlossaire.DEFENSE),
                    entree("Soldat Bleu", "Tour qui attaque les ennemis à distance.", CategorieGlossaire.DEFENSE),
                    entree("Sniper", "Tour de précision qui tire sur les ennemis à distance.", CategorieGlossaire.DEFENSE),
                    entree("Lance-Rocket", "Tour qui lance des roquettes sur les ennemis.", CategorieGlossaire.DEFENSE),
                    entree("Soldat Rouge", "Ennemi qui avance vers la base.", CategorieGlossaire.ENNEMI),
                    entree("Véhicule", "Ennemi mobile qui suit la route vers la base.", CategorieGlossaire.ENNEMI),
                    entree("Char d'Assaut", "Ennemi blindé qui progresse vers la base.", CategorieGlossaire.ENNEMI_RESISTANT),
                    entree("Dictateur (Boss)", "Boss de l'époque contemporaine.", CategorieGlossaire.BOSS)
            )),
            4, new EpoqueGlossaire("Futur", List.of(
                    entree("Pistolet Laser", "Tour qui tire des projectiles laser.", CategorieGlossaire.DEFENSE),
                    entree("Pistolet Laser MK2", "Version améliorée du pistolet laser.", CategorieGlossaire.DEFENSE),
                    entree("Tesla", "Tour électrique qui attaque les ennemis.", CategorieGlossaire.DEFENSE),
                    entree("Tour du Temps", "Tour futuriste qui manipule le temps.", CategorieGlossaire.DEFENSE),
                    entree("Robot", "Ennemi robotique qui avance vers la base.", CategorieGlossaire.ENNEMI),
                    entree("Araignée Robot", "Ennemi robotique qui progresse sur la route.", CategorieGlossaire.ENNEMI),
                    entree("Générateur", "Ennemi mécanique qui avance vers la base.", CategorieGlossaire.ENNEMI_RESISTANT),
                    entree("IA (Boss)", "Boss de l'époque futuriste.", CategorieGlossaire.BOSS)
            ))
    );

    private Glossaire() {
    }

    public static EpoqueGlossaire pourEpoque(int epoque) {
        return EPOQUES.getOrDefault(epoque, EPOQUE_INCONNUE);
    }

    private static EntreeGlossaire entree(String nom, String description, CategorieGlossaire categorie) {
        return new EntreeGlossaire(nom, description, categorie);
    }
}
