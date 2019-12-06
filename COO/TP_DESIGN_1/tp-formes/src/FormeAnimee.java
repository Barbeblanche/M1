/**
 * Version maladroite de la FormeAnimee :
 * <ul>
 * <li>cette version gère toutes les variantes (clignotement, pulsation,
 * rotation). Ce n'est ni extensible, ni très clair.</li>
 * <li>une version correcte devrait utiliser les bons design patterns pour
 * découpler les différentes variantes d'animation.</li>
 * </ul>
 * @author Guillaume Huard
 */
class FormeAnimee {
	Forme forme;
	boolean clignotement, pulsation, rotation;
	// Pour le clignotement
	int lenteur;
	int etape;
	// Pour les pulsations
	int etapes;
	int amplitude;
	int base;
	int courant;
	// Pour la rotation
	int vitesse;

	/**
	 * Constructeur prenant une Forme en paramètre. Toutes les opérations gérées par la Forme lui sont déléguées.
	 * @param f Forme à laquelle déléguer les opérations.
	 */
	FormeAnimee(Forme f) {
		forme = f;
	}

	/**
	 * Active le clignotement de la forme animée.
	 * @param v vitesse de clignotement.
	 */
	void activerClignotement(int v) {
		lenteur = v;
		etape = -lenteur;
		clignotement = true;
	}

	 /**
	 * Active les pulsations de la forme animée.
	  * @param e nombre d'étapes d'un cycle de pulsations.
	  * @param a amplitude de la pulsation.
	  * @param b taille de base de la forme.
	  */
	void activerPulsations(int e, int a, int b) {
		etapes = e;
		amplitude = a;
		base = b;
		courant = 0;
		pulsation = true;
	}

	/**
	 * Active la rotation de la forme animée.
	 * @param v vitesse de rotation.
	 */
	void activerRotation(int v) {
		vitesse = v;
		rotation = true;
	}

	/**
	 * Avance d'une étape dans l'animation de la forme.
	 */
	void avancerEtape() {
		// Pour le clignotement éventuel
		etape++;
		if (etape >= lenteur)
			etape = -lenteur;
		// Pour les pulsations éventuelles
		if (pulsation) {
			double angle = 2 * Math.PI * courant / etapes;
			int ajout = (int) (amplitude * (Math.sin(angle) + 1) / 2);
			fixerTaille(base + ajout);
			courant++;
			if (courant > etapes)
				courant = 0;
		}
		// Pour la rotation éventuelle
		if (rotation)
			forme.tourner(vitesse);
	}

	/**
	 * Dessine la forme (voir Forme).
	 */
	void dessiner() {
		if (!clignotement || (etape < 0))
			forme.dessiner();
	}

	/**
	 * Fixe la taille de la forme (voir Forme).
	 * @param t taille.
	 */
	void fixerTaille(int t) {
		forme.fixerTaille(t);
	}

	/**
	 * Fixe la position du centre de la forme (voir Forme).
	 * @param x abscisse.
	 * @param y ordonnée.
	 */
	void fixerPosition(int x, int y) {
		forme.fixerPosition(x, y);
	}

	/**
	 * Fait tourner la forme (voir Forme).
	 * @param angle angle.
	 */
	void tourner(double angle) {
		forme.m().tournerGauche(angle);
	}
}
