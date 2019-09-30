package tp1;

public abstract class Titre {
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
	
	public abstract String toString();
	
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
