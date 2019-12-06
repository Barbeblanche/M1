/**
 * Fabrique permettant de centrer les texte et d'aligner à gauche les paragraphes.
 * @author Guillaume Huard
 */
class FabriqueDefaut extends FabriqueTexte {
	@Override
	Texte nouveauTexte(String t) {
		return new TexteCentre(t);
	}

	@Override
	Paragraphe nouveauParagraphe() {
		return new ParagrapheGauche();
	}

}
