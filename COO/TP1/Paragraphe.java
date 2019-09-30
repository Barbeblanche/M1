import java.util.ArrayList;

public class Paragraphe {
	ArrayList<String> texte;
	int largeur;
	int nbLignes;
	
	public Paragraphe() {
		texte = new ArrayList<String>();
		largeur = 100;
		nbLignes = 0;
	}
	
	private Paragraphe(int l) {
		texte = new ArrayList<String>();
		largeur = l;
		nbLignes = 0;
	}
	
	void fixeLargeur(int l) {
		largeur = l;
		Paragraphe p = new Paragraphe(l);
		for(String ligne : texte) {
			String[] mots = ligne.split("\\s\\s*");
			for (int i = 0; i < mots.length; i++) {
				p.ajoute(mots[i]);
			}
		}
		texte = p.texte;
		largeur = p.largeur;
		nbLignes = p.nbLignes;
	}
	
	void ajoute(String mot) {
		if (texte.isEmpty() || texte.get(nbLignes).isEmpty()) {
			texte.add(mot);
		}
		else {
			String ligne = texte.get(nbLignes);
			int tailleLigne = ligne.length();
			int tailleMot = mot.length();
			if (tailleLigne + tailleMot < largeur) {
				ligne += " " + mot;
				texte.set(nbLignes, ligne);
			}
			else {
				nbLignes++;
				texte.add(mot);
			}
		}
	}
	
	int nbLignes() {
		return nbLignes;
	}
	
	public String toString() {
		// Pour toutes les lignes de texte[], on les centre et on les affiche
		
		String texteCentre = "";
		int nbEspaces = 0;
		for(String ligne : texte) {
			String s = "";
			nbEspaces = (largeur - ligne.length()) / 2;
			for(int i = 0; i < nbEspaces; i++) {
				s += " ";
			}
			texteCentre += s + ligne + s + "\n";
		}
		return texteCentre;
	}
	
}
