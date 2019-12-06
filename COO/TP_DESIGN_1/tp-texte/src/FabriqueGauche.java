
public class FabriqueGauche extends Fabrique{

	@Override
	Paragraphe creerParagraphe() {
		return new ParagrapheGauche();
	}

}
