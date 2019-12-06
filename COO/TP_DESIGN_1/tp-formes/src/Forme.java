/**
 * Classe abstraite généralisant la notion de forme. Destinée à être spécialisée
 * pour représenter une forme particulière.
 * @author Guillaume Huard
 */
public abstract class Forme {
	MachineTrace m;
	int taille;
	int x, y;

	/**
	 * Pas de paramètres particuliers, tout se règle via les autres méthodes.
	 * @param m MachineTrace à utiliser pour le dessin.
	 */
	Forme(MachineTrace mach) {
		m = mach;
	}

	/**
	 * Fixe le coté d'un carré englobant la forme.
	 * @param t coté du carré englobant.
	 */
	void fixerTaille(int t) {
		taille = t;
	}

	/**
	 * Fixe la position du centre du carré englobant.
	 * @param x abscisse du centre.
	 * @param y ordonnée du centre.
	 */
	void fixerPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Méthode commune à toutes les versions spécialisées pour remettre la plume.
	 * levée au centre.
	 */
	protected void reset() {
		m.lever();
		m.placer(x, y);
	}

	/**
	 * Renvoie la MachineTrace utilisée pour dessiner cette forme.
	 * @return MachineTrace utilisée par la forme.
	 */
	MachineTrace m() {
		return m;
	}

	/**
	 * Fait tourner la forme vers la gauche de l'angle donné.
	 * @param angle angle de la rotation.
	 */
	void tourner(double angle) {
		m.tournerGauche(angle);
	}

	/**
	 * Dessine la forme dans sa MachineTrace.
	 */
	void dessiner() {
		throw new RuntimeException("Bugs : dessiner ne doit pas être appelé dans Forme");
	}
}
