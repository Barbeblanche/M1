import java.io.InputStream;
import java.util.Scanner;

class Directeur {
	Batisseur batisseur;
	InputStream in;

	/**
	 * Crée un nouveau directeur qui travaille avec un bâtisseur donné sur un flux
	 * d'entrée donné.
	 * @param b le bâtisseur.
	 * @param i le flux d'entrée.
	 */
	Directeur(Batisseur b, InputStream i) {
		batisseur = b;
		in = i;
	}

	/**
	 * Construit un document à partir du bâtisseur et du flux d'entrée internes. Le
	 * flux d'entrée sera constitué d'un bloc de texte suivi d'un nombre quelconque
	 * de chapitre. Une ligne dont le premier mot est CHAPITRE démarre un nouveau
	 * chapitre dont le titre est le reste de la ligne. Les paragraphes sont séparés
	 * par une ligne vide.
	 * @param t Entête du document
	 * @param in Flux d'entrée
	 */
	void construit() {
		Scanner s = new Scanner(in);
		while (s.hasNextLine()) {
			String ligne = s.nextLine().trim();
			if (ligne.length() == 0) {
				batisseur.ajouterParagraphe();
			} else {
				String[] mots = ligne.split("\\s\\s*");
				if (mots[0].equals("CHAPITRE")) {
					batisseur.ajouterChapitre(ligne);
				} else {
					for (int i = 0; i < mots.length; i++)
						batisseur.ajouterMot(mots[i]);
				}
			}
		}
		s.close();
	}
}
