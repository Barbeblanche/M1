package tp1;
import java.io.PrintStream;
import java.util.ArrayList;

public class BlocDeTexte {
	private ArrayList<Paragraphe> paragraphes;
	private Chapitre chapitre;
	
	public BlocDeTexte() {
		setParagraphes(new ArrayList<Paragraphe>());
	}
	
	public void ajoute(Paragraphe p) {
		getParagraphes().add(p);
	}
	
	public void ecris(PrintStream p) {
		System.setOut(p);
		for (Paragraphe para : this.paragraphes) {
			System.out.println(para);
		}
	}
	
	public void fixeLargeur(int l) {
		for (Paragraphe para : getParagraphes()) {
			para.fixeLargeur(l);
		}
	}
	
	public String toString() {
		if (getChapitre() != null){
			return "Nature : Chapitre ; Nombre de paragraphes : " + getParagraphes().size();
		}else {
			return "Nature : bloc de texte ; Nombre de paragraphes : " + getParagraphes().size();
		}
		
	}

	ArrayList<Paragraphe> getParagraphes() {
		return paragraphes;
	}

	void setParagraphes(ArrayList<Paragraphe> paragraphes) {
		this.paragraphes = paragraphes;
	}

	public Chapitre getChapitre() {
		return chapitre;
	}

	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}
}
