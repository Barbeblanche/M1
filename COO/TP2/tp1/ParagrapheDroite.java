package tp1;

public class ParagrapheDroite extends Paragraphe{
	public String toString() {
		// Pour toutes les lignes de texte[], on les centre et on les affiche
		
		String texteDroite = "";
		int nbEspaces = 0;
		for(String ligne : texte) {
			String s = "";
			nbEspaces = (largeur - ligne.length()) ;
			for(int i = 0; i < nbEspaces; i++) {
				s += " ";
			}
			texteDroite += s + ligne + "\n";
		}
		return texteDroite;
	}
}
