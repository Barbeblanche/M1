/**
 * Fabrique permettant d'aligner à droite les textes et paragraphes.
 * @author Guillaume Huard
 */
class FabriqueDroite extends FabriqueTexte {
	@Override
	Texte nouveauTexte(String t) {
		return new TexteDroite(t);
	}

	@Override
	Paragraphe nouveauParagraphe() {
		return new ParagrapheDroite();
	}

}
