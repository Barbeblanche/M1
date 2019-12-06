
public class FabriqueDroite extends Fabrique {

	@Override
	Paragraphe creerParagraphe() {
		return new ParagrapheDroite();
	}

}
