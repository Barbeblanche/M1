import java.io.PrintStream;

/**
 * Un document constitué d'un entête, d'un bloc de texte (prologue) et d'un nombre quelconque de chapitres.
 * @author Guillaume Huard
 */
public class Document extends Batisseur {
	private Entete titre;
	private BlocDeTexte prologue;
	private Chapitre[] chapitres;
	private int nbChap;
	// Partie utilisée par le bâtisseur
	BlocDeTexte c;
	Paragraphe p;

	@Override
	void ajouterMot(String mot) {
		if (p == null)
			p = FabriqueTexte.instance().nouveauParagraphe();
		p.ajoute(mot);
	}

	@Override
	void ajouterParagraphe() {
		if (p != null) {
			c.ajoute(p);
			p = null;
		}
	}

	@Override
	void ajouterChapitre(String titre) {
		chapitres[nbChap] = new Chapitre(titre);
		c = chapitres[nbChap].bloc();
		nbChap++;
	}

	@Override
	Document resultat() {
		return this;
	}

	/**
	 * Construit un document vide destiné à être complété (via sa partie batisseur) à partir d'un entête.
	 * @param t Entête du document
	 */
	public Document(Entete t) {
		titre = t;
		prologue = new BlocDeTexte();
		chapitres = new Chapitre[100];
		nbChap = 0;
		
		c = prologue;
		p = null;
	}

	/**
	 * Fixe la largeur de l'entête, du bloc de texte initial et de tous les chapitres
	 * du document.
	 * @param f Nouvelle largeur
	 */
	public void fixeLargeur(int f) {
		titre.fixeLargeur(f);
		prologue.fixeLargeur(f);
		for (int i = 0; i < nbChap; i++) {
			chapitres[i].fixeLargeur(f);
		}
	}

	/**
	 * Ecris la représentation textuelle du document sur le flux de sortie donné.
	 * @param p Flux de sortie
	 */
	public void ecris(PrintStream p) {
		if (titre != null) {
			p.println(titre);
			p.println();
		}
		prologue.ecris(p);
		for (int i = 0; i < nbChap; i++) {
			chapitres[i].ecris(p);
		}
	}

	/**
	 * Renvoie des infos textuelles sur la nature du texte (Document)
	 * son titre, son auteur et son nombre de chapitres.
	 * @return Descriptif du texte
	 */
	@Override
	public String toString() {
		return "Document " + titre.titre() + " (" + titre.auteur() + ") : " + nbChap + " chapitres.";
	}
}
