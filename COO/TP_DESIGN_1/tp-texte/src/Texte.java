/**
 * Texte, typiquement un titre, mais peut s'appliquer à tout texte.
 * Classe destinée à être spécialisée en TexteCentré, TexteDroite, etc.
 * 
 * @author Guillaume Huard
 */
public abstract class Texte {
	private String texte;
	protected int largeur;

	/**
	 * Construit un texte de largeur 80 à partir d'une chaîne de caractères.
	 * @param t La chaîne de caractères
	 */
	public Texte(String t) {
		texte = t;
		largeur = 80;
	}

	/**
	 * Change la largeur du texte.
	 * @param l Nouvelle largeur
	 */
	public void fixeLargeur(int l) {
		largeur = l;
	}
	
	/**
	 * Renvoie la chaîne de caractères de ce texte sans mise en page.
	 * @return Texte brut
	 */
	public String texte() {
		return texte;
	}
	
	// Fonction interne permettant de produire le texte indenté
	protected String indente(int nbEspaces) {
		StringBuilder resultat = new StringBuilder();

		// Version plus concise et efficace de l'ajout d'espaces
		resultat.append(new String(new char[nbEspaces]).replace('\0', ' '));
		resultat.append(texte);
		return resultat.toString();
	}
}
