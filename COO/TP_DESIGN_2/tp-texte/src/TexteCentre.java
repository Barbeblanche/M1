/**
 * Texte centré, typiquement un titre, mais peut s'appliquer à tout texte centré.
 * Le centrage est réalisé en ajoutant suffisamment d'espaces à gauche du texte.
 * 
 * @author Guillaume Huard
 */
public class TexteCentre extends Texte {
	/**
	 * Construit un texte centré de largeur 80 à partir d'une chaîne de caractères.
	 * @param t La chaîne de caractères à centrer
	 */
	public TexteCentre(String t) {
		super(t);
	}

	/**
	 * Renvoie le texte centré : une chaîne de caractères avec suffisamment d'espaces
	 * à gauche pour que le texte soit centré par rapport à sa largeur
	 * @returns Le texte centré
	 */
	@Override
	public String toString() {
		int nbEspaces = (largeur - texte().length()) / 2;
		return indente(nbEspaces);
	}
}
