/**
 * On place la fabrication de tous les éléments alignables dans une seule
 * fabrique pour avoir une présentation cohérente (tout aligné à gauche ou
 * droite par exemple). Le défaut est que cela demande plus de classes pour
 * implémenter toutes les combinaisons possibles (mais on ne le fera pas ici).
 * @author Guillaume Huard
 */
abstract class FabriqueTexte {
	// Singleton choisissant l'instance en fonction de l'environnement
	protected static FabriqueTexte instance;

	protected FabriqueTexte() {
	}

	/**
	 * Singleton fournissant la bonne instance de fabrique selon la valeur de la
	 * variable d'environnement ALIGNEMENT (GAUCHE, DROITE ou autre).
	 * @return la bonne instance de fabrique.
	 */
	static FabriqueTexte instance() {
		if (instance == null) {
			String choix = System.getenv("ALIGNEMENT");
			if("GAUCHE".equals(choix))
				instance = new FabriqueGauche();
			else if ("DROITE".equals(choix))
				instance = new FabriqueDroite();
			else
				instance = new FabriqueDefaut();
		}
		return instance;
	}

	/**
	 * Crée un nouvel élément de texte alignable sur une seule ligne.
	 * @param t le texte.
	 * @return le texte aligné.
	 */
	abstract Texte nouveauTexte(String t);

	/**
	 * Crée un nouveau paragraphe alignable.
	 * @return le paragraphe aligné.
	 */
	abstract Paragraphe nouveauParagraphe();
}
