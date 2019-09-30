package tp1;

public class ParagrapheGauche extends Paragraphe{
	public String toString() {
		// Pour toutes les lignes de texte[], on les centre et on les affiche
		
		String texteGauche = "";
		int nbEspaces = 0;
		for(String ligne : texte) {
			String s = "";
			nbEspaces = (largeur - ligne.length());
			for(int i = 0; i < nbEspaces; i++) {
				s += " ";
			}
			texteGauche += ligne + s +"\n";
		}
		return texteGauche;
	}
}
