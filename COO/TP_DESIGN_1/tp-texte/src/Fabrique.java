
public abstract class Fabrique {
	abstract Paragraphe creerParagraphe() ;
	
	public void construction() {
		Paragraphe p = creerParagraphe();
	}
	
}
