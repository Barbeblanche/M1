
public class Titre {
	String texte;
	int largeur;
	
	public Titre(String t) {
		texte = t;
		largeur = 50;
	}
	
	void fixeLargeur (int l) {
		setLargeur(l);
	}
	
	String texte() {
		return getTexte();
	}
	
	public String toString() {
		int nbEspaces = (largeur - texte.length()) / 2;
		String texteCentre = "";
		
		for(int i = 0; i < nbEspaces; i++) {
			texteCentre += " ";
		}
		texteCentre += texte;
		for(int i = 0; i < nbEspaces; i++) {
			texteCentre += " ";
		}
		return texteCentre;
	}
	
	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int longueur) {
		this.largeur = longueur;
	}
	
	
}
