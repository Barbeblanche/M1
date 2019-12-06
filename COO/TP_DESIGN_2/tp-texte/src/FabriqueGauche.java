/**
 * Fabrique permettant d'aligner à gauche les textes et paragraphes.
 * @author Guillaume Huard
 */
class FabriqueGauche extends FabriqueTexte {
	@Override
	Texte nouveauTexte(String t) {
		return new TexteGauche(t);
	}

	@Override
	Paragraphe nouveauParagraphe() {
		return new ParagrapheGauche();
	}

}
